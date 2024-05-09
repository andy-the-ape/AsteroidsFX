package dk.sdu.mmmi.cbse.common.enemy;

import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.entities.EntityColor;
import dk.sdu.mmmi.cbse.common.data.entities.EntityType;

public class Enemy extends Entity {
    public Enemy() {
        this.setColor(EntityColor.RED);
        this.setType(EntityType.ENEMY);
        this.setLife(1);
        this.setPolygonCoordinates(-5,-5,10,0,-5,5,-8,0);
        this.setCollisionBoxRadius(8);
    }
}
