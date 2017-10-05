function setup() {
    createCanvas(1000, 600);
    noStroke();
    smooth();
}

function draw() {
    $.getJSON("/getData/" + worldId + "/" + bloopId, function (json) {
        background(51);
        $.each(json.food, function () {
            fill(200, 50, 0);
            ellipse(this.location.x, this.location.y, this.size, this.size);
        });

        $.each(json.bloops, function () {
            fill(255);
            ellipse(this.location.x, this.location.y, this.size, this.size);
        });
    });

    $.post("/move", {worldId: worldId, bloopId: bloopId, x: mouseX, y: mouseY});
}