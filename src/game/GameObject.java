package game;

import utilities.Vector2D;

import java.awt.*;

public abstract class GameObject {
    public Vector2D position;
    public Vector2D velocity;
    public boolean dead;
    public double radius;
    public int hitCount = 0;

    public int deltaTime; // Used for deltaTime calculations
    private long _lastTime = System.nanoTime();

    public GameObject(Vector2D position, Vector2D velocity, double radius) {
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
    }

    public void hit() {
        hitCount += 1;
        this.dead = true;
    }

    public boolean overlap(GameObject other) {
        final double a = this.radius + other.radius; // get the sum radii of both GameObjects
        final double dx = this.position.x - other.position.x; // get the difference
        final double dy = this.position.y - other.position.y;

        return a * a > (dx * dx + dy * dy);
    }

    public void collisionHandling(GameObject other) {
        if (this.getClass() != other.getClass() && this.overlap(other) && Game.canStart) {
            this.hit();
            other.hit();
        }
    }

    public void update() {
        position.addScaled(velocity, Constants.DT);
        position.wrap(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
//        Obtain the deltaTime
        long time = System.nanoTime();
        deltaTime = (int) ((time - _lastTime) / 1000000);
        _lastTime = time;
    }

    public abstract void draw(Graphics2D g2);
}