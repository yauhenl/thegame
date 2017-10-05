function setup() {
    createCanvas(1000, 600);
    background(33);
}

function draw() {
    $.getJSON("/getData/" + worldId + "/" + bloopId, function (json) {
        $.each(json.food, function () {
            noStroke();
            fill(200, 50, 0);
            rect(this.x, this.y, 8, 8);
        });

        $.each(json.bloops, function () {
            noStroke();
            fill(map(size, 0, 1000, 33, 255), 200);
            ellipse(this.x, this.y, this.size, this.size);
            fill(255);
            text(this.id, this.x, this.y);
        });
    });
}

function mouseMoved() {
    $.post("/move", {worldId: worldId, bloopId: bloopId, x: mouseX, y: mouseY});
}