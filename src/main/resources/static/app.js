const secret = window.location.pathname.substring(1);

const currentUser =
    secret === "blue"
        ? "A"
        : "B";

document.getElementById("chatTitle").innerHTML =
    secret === "blue"
        ? "💙 Blue"
        : "🌙 Moon";

const textarea =
    document.getElementById("message");

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

    container.innerHTML = "";

    messages.forEach(m => {

        const mine =
            m.sender === currentUser;

        container.innerHTML += `

<div class="message ${mine ? "mine" : "other"}">

<div class="sender">

${mine ? "You" : m.sender}

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

    container.scrollTop =
        container.scrollHeight;

}

async function loadMessages() {

    const response =
        await fetch("/messages/conversation/" + secret);

    render(await response.json());

}

async function sendMessage() {

    const message =
        textarea.value.trim();

    if (message === "")
        return;

    await fetch("/messages/" + secret, {

        method: "POST",

        headers: {

            "Content-Type": "application/json"

        },

        body: JSON.stringify({

            message

        })

    });

    textarea.value = "";

    textarea.style.height = "50px";

}

loadMessages();

const socket =
    new SockJS("/ws");

const stompClient =
    new StompJs.Client({

        webSocketFactory: () => socket,

        reconnectDelay: 5000

    });

stompClient.onConnect = () => {

    stompClient.subscribe(

        "/topic/" + currentUser,

        () => {

            loadMessages();

        }

    );

};

stompClient.activate();