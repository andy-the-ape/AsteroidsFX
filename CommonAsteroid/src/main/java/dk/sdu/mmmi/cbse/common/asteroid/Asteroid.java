package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityColor;
import dk.sdu.mmmi.cbse.common.data.EntityType;

public class Asteroid extends Entity {
    public Asteroid() {
        this.setColor(EntityColor.GRAY);
        this.setType(EntityType.ASTEROID);
    }
}
