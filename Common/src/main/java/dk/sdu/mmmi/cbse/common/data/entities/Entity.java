package dk.sdu.mmmi.cbse.common.data.entities;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    private EntityColor color;
    private EntityType type;
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private double rotationSpeed;
    private double speed;
    private double collisionBoxRadius;
    private int life;

    public String getID() {
        return ID.toString();
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setPolygonCoordinates(double... polygonCoordinates) {
        this.polygonCoordinates = polygonCoordinates;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getCollisionBoxRadius() {
        return collisionBoxRadius;
    }

    public void setCollisionBoxRadius(double collisionBoxRadius) {
        this.collisionBoxRadius = collisionBoxRadius;
    }

    public EntityColor getColor() {
        return color;
    }

    public void setColor(EntityColor color) {
        this.color = color;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

}
