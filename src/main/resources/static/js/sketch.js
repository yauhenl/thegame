function setup() {
    createCanvas(1000, 600);
    noStroke();
    fill(255, 153);
    background(51);
}

function draw() {
    $.getJSON("/getData/" + worldId + "/" + bloopId, function (json) {
        $.each(json.food, function () {
            noStroke();
            fill(200, 50, 0);
            ellipse(this.location.x, this.location.y, this.size, this.size);
        });

        $.each(json.bloops, function () {
            noStroke();
            fill(map(size, 0, 1000, 33, 255), 200);
            ellipse(this.location.x, this.location.y, this.size, this.size);
            fill(255);
            text(this.id, this.location.x, this.location.y);
        });
    });

    $.post("/move", {worldId: worldId, bloopId: bloopId, x: mouseX, y: mouseY});
}