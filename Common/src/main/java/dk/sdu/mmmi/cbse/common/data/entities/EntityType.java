package dk.sdu.mmmi.cbse.common.data.entities;

import java.util.stream.Collector;

public enum EntityType {
    PLAYER("Player"),
    ENEMY("Enemy"),
    BULLET("Bullet"),
    ASTEROID("Asteroid");

    EntityType(String friendlyName) {
    }

}
