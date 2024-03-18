package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();

    private EntityType type;
    
    private double[] polygonCoordinates;
    private EntityColor color;

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
    private float radius;
    private boolean isCollided;

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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public EntityColor getColor() {
        return color.name();
    }

    public void setColor(EntityColor color) {
        this.color = color;
    }

    public boolean isCollided() {
        return isCollided;
    }

    public void setCollided(boolean collided) {
        isCollided = collided;
    }
}
