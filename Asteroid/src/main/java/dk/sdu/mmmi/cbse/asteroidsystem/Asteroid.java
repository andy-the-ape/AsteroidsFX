package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.entities.EntityColor;
import dk.sdu.mmmi.cbse.common.data.entities.EntityType;

public class Asteroid extends Entity {
    public Asteroid(float life, double... polygonCoordinates) {
        this.setColor(EntityColor.GRAY);
        this.setType(EntityType.ASTEROID);
        this.setLife(life);
        this.setPolygonCoordinates(polygonCoordinates);
    }
}
