var stompClient = null;

$(function () {
    var socket = new SockJS('/thegame-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/data', function (data) {
            var json = JSON.parse(data.body);
        });
    });

    if (stompClient.connected) {
        stompClient.send("/thegame/move", {}, JSON.stringify({
            worldId: worldId,
            bloopId: bloopId,
            x: mouseX,
            y: mouseY
        }));
    }
});