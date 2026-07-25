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
        ? "💙 Blue"
        : "🌙 Moon";

const avatar =
    document.getElementById("avatar");

avatar.innerHTML =
    secret === "blue"
        ? "💙"
        : "🌙";

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

function formatTime(date) {

    return new Date(date)
        .toLocaleTimeString([], {

            hour: "2-digit",
            minute: "2-digit"

        });

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
                ${m.message}
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

        reconnectDelay: 5000

    });

stompClient.onConnect = () => {

    stompClient.onWebSocketClose = () => {

        document.getElementById(
            "connectionStatus"
        ).innerHTML = "🔴 Disconnected";

        document.getElementById(
            "connectionStatus"
        ).className = "disconnected";

    };

    document.getElementById(
        "connectionStatus"
    ).innerHTML = "🟢 Connected";

    document.getElementById(
        "connectionStatus"
    ).className = "connected";

    stompClient.subscribe(

        "/topic/" + currentUser,

        () => {

            loadMessages();

        }

    );

};

stompClient.activate();