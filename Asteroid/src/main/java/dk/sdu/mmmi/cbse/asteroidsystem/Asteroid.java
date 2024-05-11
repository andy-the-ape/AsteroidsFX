package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.entities.EntityColor;
import dk.sdu.mmmi.cbse.common.data.entities.EntityType;

public class Asteroid extends Entity {

    public Asteroid(double speed, double sizeModifier) {
        this.setColor(EntityColor.GRAY);
        this.setType(EntityType.ASTEROID);
        this.setLife(2);
        this.setSpeed(speed);
        double collisionBoxRadius = 1;
        double[] basePolygonCoordinates = {-8, 0, -6, 6, 0, 8, 6, 6, 8, 0, 6, -6, 0, -8, -6, -6};
        for (int i = 0; i < basePolygonCoordinates.length; i++) {
            basePolygonCoordinates[i] *= sizeModifier;
            if (basePolygonCoordinates[i] > collisionBoxRadius) {
                collisionBoxRadius = basePolygonCoordinates[i];
            }
        }
        this.setPolygonCoordinates(basePolygonCoordinates);
        this.setCollisionBoxRadius(collisionBoxRadius);
    }

    public Asteroid(Entity parentAsteroid, double sizeModifier) {
        this.setColor(EntityColor.LIGHTGRAY);
        this.setType(EntityType.ASTEROID);
        this.setLife(1);
        this.setX(parentAsteroid.getX());
        this.setY(parentAsteroid.getY());
        this.setSpeed(parentAsteroid.getSpeed());
        double[] polygonCoordinates = parentAsteroid.getPolygonCoordinates().clone();
        for (int i = 0; i < parentAsteroid.getPolygonCoordinates().length; i++) {
            polygonCoordinates[i] *= sizeModifier;
        }
        this.setPolygonCoordinates(polygonCoordinates);
        this.setCollisionBoxRadius(parentAsteroid.getCollisionBoxRadius() * sizeModifier);
    }
}
