var stompClient = null;
var json = null;

function setup() {
    var canvas = createCanvas(1280, 720);
    noStroke();
    smooth();
    canvas.parent('sketch-holder');

    var socket = new SockJS('/thegame-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/user/topic/data', function (data) {
            json = JSON.parse(data.body);
        });
    });
}

function draw() {
    if (json != null) {
        background(51);
        $.each(json.food, function () {
            fill(200, 50, 0);
            ellipse(this.location.x, this.location.y, this.size, this.size);
        });

        $.each(json.bloops, function () {
            fill(255);
            ellipse(this.location.x, this.location.y, this.size, this.size);
        });
    }

    if (stompClient.connected) {
        stompClient.send("/thegame/move", {}, JSON.stringify({
            worldId: worldId,
            bloopId: bloopId,
            x: mouseX,
            y: mouseY
        }));
    }
}