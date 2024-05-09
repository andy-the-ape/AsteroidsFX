package dk.sdu.mmmi.cbse.common.data.entities;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private EntityColor color;
    private EntityType type;
    
    private double[] polygonCoordinates;


    // Vector data:
    private double x;
    private double y;
    private float dx;
    private float dy;
    private float rotation;
    private float maxSpeed;
    private float acceleration;
    private float deceleration;
    private float rotationSpeed;
    private float collisionBoxRadius;
    private float life;

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

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    public float getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(float rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public float getCollisionBoxRadius() {
        return collisionBoxRadius;
    }

    public void setCollisionBoxRadius(float collisionBoxRadius) {
        this.collisionBoxRadius = collisionBoxRadius;
    }

    public EntityColor getColor() {
        return color;
    }

    public void setColor(EntityColor color) {
        this.color = color;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }
}
