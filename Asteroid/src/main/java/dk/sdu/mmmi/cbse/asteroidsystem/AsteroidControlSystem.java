package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.entities.EntityType;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {

    Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            // Handling out of bounds
            checkBoundsAndDeleteOutOfBoundAsteroid(gameData, world, asteroid);

            // Movement
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));
            asteroid.setX(asteroid.getX() + changeX * asteroid.getSpeed());
            asteroid.setY(asteroid.getY() + changeY * asteroid.getSpeed());
        }

        // Making more asteroids when needed
        if (world.getEntities().stream()
                .filter(e -> e.getType().equals(EntityType.ASTEROID))
                .count() < 10) {
            world.addEntity(createAsteroid(gameData));
        }

    }

    public Entity createAsteroid(GameData gameData) {
        double[] polygonCoordinates = {-8,0,-6,6,0,8,6,6,8,0,6,-6,0,-8,-6,-6};
        double speed = random.nextDouble(0.2,0.6);
        double sizeModifier = random.nextDouble(1.5,3);

        Asteroid asteroid = new Asteroid(speed, sizeModifier);
        setInitialSpawnPointAndRotation(gameData, asteroid);
        return asteroid;
    }

    public Entity[] createSplitAsteroids(Asteroid originalAsteroid) {
        // How many pieces to split into?
        int pieces = random.nextInt(2,5);
        Entity[] splitAsteroids = new Entity[pieces];

        for (int i = 0; i < splitAsteroids.length; i++) {
            Asteroid asteroid = new Asteroid(originalAsteroid);
            asteroid.setRotation(random.nextInt(361));
        }
        return splitAsteroids;
    }

    public void setInitialSpawnPointAndRotation(GameData gameData, Entity asteroid) {
        double xBound = gameData.getDisplayWidth();
        double yBound = gameData.getDisplayHeight();

        double xCoord = random.nextDouble(xBound);
        double yCoord = random.nextDouble(yBound);

        double xCenter = xBound/2;
        double yCenter = yBound/2;

        // Deciding whether to spawn outside the top, left, bottom or right boundary.
        if (random.nextInt(4) > 1) {
            //Top
            if (xCoord > yCoord) {
                asteroid.setX(xCoord);
                asteroid.setY(-20);

                //Setting rotation to point it at the center of the screen
                double angleDeg = Math.toDegrees(Math.atan2(yCenter - asteroid.getY(), xCenter - asteroid.getX()));
                if (angleDeg < 0) {
                    angleDeg += 360;
                }
                asteroid.setRotation(angleDeg);
            }

            //Left
            if (yCoord > xCoord) {
                asteroid.setX(-20);
                asteroid.setY(yCoord);

                //Setting rotation to point it at the center of the screen
                double angleDeg = Math.toDegrees(Math.atan2(yCenter - asteroid.getY(), xCenter - asteroid.getX()));
                if (angleDeg < 0) {
                    angleDeg += 360;
                }
                asteroid.setRotation(angleDeg);
            }
        }
        else {
            //Bottom
            if (xCoord > yCoord) {
                asteroid.setX(xCoord);
                asteroid.setY(yBound + 20);

                //Setting rotation to point it at the center of the screen
                double angleDeg = Math.toDegrees(Math.atan2(yCenter - asteroid.getY(), xCenter - asteroid.getX()));
                if (angleDeg < 0) {
                    angleDeg += 360;
                }
                asteroid.setRotation(angleDeg);
            }

            //Right
            if (yCoord > xCoord) {
                asteroid.setX(xBound + 20);
                asteroid.setY(yCoord);

                //Setting rotation to point it at the center of the screen
                double angleDeg = Math.toDegrees(Math.atan2(yCenter - asteroid.getY(), xCenter - asteroid.getX()));
                if (angleDeg < 0) {
                    angleDeg += 360;
                }
                asteroid.setRotation(angleDeg);
            }
        }
    }

    public void checkBoundsAndDeleteOutOfBoundAsteroid(GameData gameData, World world, Entity asteroid) {
        double xBound = gameData.getDisplayWidth();
        double yBound = gameData.getDisplayHeight();

        if (asteroid.getX() > xBound + 40 || asteroid.getX() < -40 || asteroid.getY() > yBound + 40 || asteroid.getY() < -40) {
            world.removeEntity(asteroid);
        }
    }

}
