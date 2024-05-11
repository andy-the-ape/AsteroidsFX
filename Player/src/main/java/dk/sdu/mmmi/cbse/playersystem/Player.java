package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.entities.EntityColor;
import dk.sdu.mmmi.cbse.common.data.entities.EntityType;

public class Player extends Entity {
    public Player() {
        this.setColor(EntityColor.CYAN);
        this.setType(EntityType.PLAYER);
        this.setLife(1);
        this.setPolygonCoordinates(-5,-5,10,0,-5,5,-2,0);
        this.setCollisionBoxRadius(8);
        this.setSpeed(1);
    }
}
