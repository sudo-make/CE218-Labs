package game;

import utilities.Vector2D;
import java.awt.*;

public class Saucers extends Ship {

    Controller ctr;
    PlayerShip playerShip;

    public Saucers(Controller ctrl, PlayerShip playerShip) {
        super(new Vector2D(300, 40), new Vector2D(0, 0), new Vector2D(0, 1), ctrl, 8);
        this.ctr = ctrl;
        this.playerShip = playerShip;
    }

    @Override
    public void hit() {
        super.hit();
    }

    @Override
    public void update() {
        super.update();
        direction.rotate(STEER_RATE * ctrl.action().turn * Constants.DT);
        velocity.addScaled(direction, ctrl.action().thrust * MAG_ACC * Constants.DT);
        velocity.mult(1 - (DRAG * Constants.DT));

        if (inRange()) {
            double angle = position.angle(playerShip.position);
            direction.rotate(angle);
            ctrl.action().thrust = 0;
            ctrl.action().turn = 0;
            ctrl.action().shoot = true;
        }
        System.out.println(inRange());
    }

    private boolean inRange() {
        // Is the ship within the saucers range?
        double shootRange = 500;
        return this.position.dist(playerShip.position) <= shootRange;
    }

    @Override
    Bullet mkBullet() {
        return super.mkBullet();
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.fillOval((int)position.x, (int) position.y, (int) radius, (int) radius);
//        g2.drawOval((int) position.x, (int) position.y, (int) shootRange, (int) shootRange);
    }
}
