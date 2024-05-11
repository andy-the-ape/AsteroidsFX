package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.entities.EntityType;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity {
    EntityType shooterType;

    public Bullet(Entity shooter) {
        this.shooterType = shooter.getType();
        this.setColor(shooter.getColor());
        this.setType(EntityType.BULLET);
        this.setPolygonCoordinates(1,-1,1,1,-1,1,-1,-1);
        this.setSpeed(10);
    }
}
