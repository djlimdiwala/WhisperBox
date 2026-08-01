const CONFIG = {
    API_BASE: "/messages",
    WS_ENDPOINT: "/ws"
};

const secret = window.location.pathname.substring(1);

const currentUser =
    secret === "blue"
        ? "A"
        : "B";

document.getElementById("chatTitle").innerHTML =
    secret === "blue"
        ? "🔷 Blue"
        : "⚙️ Moon";

const avatar =
    document.getElementById("avatar");

avatar.innerHTML =
    secret === "blue"
        ? "🖥️"
        : "💻";

const textarea =
    document.getElementById("message");

textarea.focus();

textarea.addEventListener("input", () => {

    textarea.style.height = "auto";

    textarea.style.height =
        textarea.scrollHeight + "px";

});

textarea.addEventListener("keydown", e => {

    if (e.key === "Enter" && !e.shiftKey) {

        e.preventDefault();

        sendMessage();

    }

});

document
    .getElementById("sendBtn")
    .onclick = sendMessage;

    document
        .getElementById("imageBtn")
        .onclick = () => {

            document
                .getElementById("imageInput")
                .click();

        };

    document
        .getElementById("imageInput")
        .addEventListener(
            "change",
            uploadImage
        );

function formatTime(date) {

    return new Intl.DateTimeFormat(
        undefined,
        {

            day: "2-digit",

            month: "2-digit",

            hour: "2-digit",

            minute: "2-digit"

        }

    ).format(new Date(date));

}

function render(messages) {

    const container =
        document.getElementById("messages");

    if (messages.length === 0) {

        container.innerHTML = `
            <div id="emptyState">
                💬
                <h3>No messages yet</h3>
                <p>Start your private conversation.</p>
            </div>
        `;

        return;

    }

    container.innerHTML = "";

    messages.forEach(m => {

        const mine =
            m.sender === currentUser;

        container.innerHTML += `
        <div class="message ${mine ? "mine" : "other"}">

            <div class="sender">
                ${mine
                    ? "You"
                    : (currentUser === "A" ? "Moon" : "Blue")}
            </div>

            <div>

            ${m.messageType === "IMAGE"

            ? `<img
                    src="${m.message}"
                    class="chat-image"
                    onclick="showImage('${m.message}')">`

            : m.message

            }

            </div>

            <div class="time">
                ${formatTime(m.createdAt)}
            </div>

        </div>
        `;

    });

    container.scrollTo({

        top: container.scrollHeight,

        behavior: "smooth"

    });

}

async function loadMessages() {

    try {

        const response =
            await fetch(
                `${CONFIG.API_BASE}/conversation/${secret}`
            );

        const messages =
            await response.json();

        render(messages);

        document
            .getElementById("loadingScreen")
            .style.display = "none";

        document
            .getElementById("chatContainer")
            .style.display = "flex";

    }
    catch (e) {

        toast("Unable to connect.");

    }

}

function toast(message) {

    const t =
        document.getElementById("toast");

    t.innerHTML = message;

    t.style.display = "block";

    setTimeout(() => {

        t.style.display = "none";

    }, 3000);

}

async function uploadImage() {

    const file =
        document
            .getElementById("imageInput")
            .files[0];

    if (!file)
        return;

    const form =
        new FormData();

    form.append("file", file);

    try {

        const upload =
            await fetch("/upload/image", {

                method: "POST",

                body: form

            });

        const image =
            await upload.json();

        await fetch(`${CONFIG.API_BASE}/${secret}`, {

            method: "POST",

            headers: {

                "Content-Type": "application/json"

            },

            body: JSON.stringify({

                message: image.url,

                messageType: "IMAGE"

            })

        });

    }
    catch {

        toast("Image upload failed");

    }

    document.getElementById("imageInput").value = "";

}

async function sendMessage() {

    const message =
        textarea.value.trim();

    if (message === "")
        return;

    const button =
        document.getElementById("sendBtn");

    button.disabled = true;

    try {

        await fetch(
            `${CONFIG.API_BASE}/${secret}`,
            {

                method: "POST",

                headers: {

                    "Content-Type": "application/json"

                },

                body: JSON.stringify({

                    message

                })

            });

    }
    catch (e) {

        toast("Unable to send message.");

    }

    button.disabled = false;

    textarea.value = "";

    textarea.focus();

    textarea.style.height = "50px";

}

loadMessages();

const socket =
    new SockJS(CONFIG.WS_ENDPOINT);


const stompClient =
    new StompJs.Client({
        webSocketFactory: () => socket,
        reconnectDelay: 5000,
        connectHeaders: {
            user: currentUser
        }
    });

stompClient.onConnect = () => {

    stompClient.onWebSocketClose = () => {

        toast("Connection lost. Reconnecting...");

    };

    loadPresence();

    stompClient.subscribe(

        "/topic/" + currentUser,

        () => {

            loadMessages();

        }

    );

};

stompClient.activate();
async function loadPresence() {
    const other =
        currentUser === "A"
            ? "B"
            : "A";
    const response =
        await fetch("/presence/" + other);
    const presence =
        await response.json();
    const status =
        document.getElementById("connectionStatus");
    if (presence.online) {
        status.innerHTML = "🟢 Online";
        status.className = "connected";

    }
    else if (presence.lastSeen) {
        const d =
            new Date(presence.lastSeen);
            status.innerHTML =
                "Last seen " +
                d.toLocaleString([], {
                    day: "2-digit",
                    month: "2-digit",
                    hour: "2-digit",
                    minute: "2-digit"
                });
        status.className = "disconnected";
    }
    else {

        status.innerHTML =
            "Offline";
        status.className =
            "disconnected";

    }

}

async function sendHeartbeat() {

    try {

        await fetch("/presence/ping/" + currentUser, {

            method: "POST"

        });

    } catch(e) {}

}

async function startPresence() {
    await sendHeartbeat();
    await loadPresence();
    setInterval(sendHeartbeat, 20000);
    setInterval(loadPresence, 3000);
}

startPresence();

function showImage(url){

    const overlay =
        document.createElement("div");

    overlay.className = "image-overlay";

    overlay.innerHTML =
        `<img src="${url}">`;

    overlay.onclick =
        () => overlay.remove();

    document.body.appendChild(overlay);

}