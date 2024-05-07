package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class AsteroidControlSystem implements IEntityProcessingService, AsteroidSPI {

    private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    @Override
    public void process(GameData gameData, World world) {
        handleAsteroidCreation(gameData, world);

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            if (asteroid.isCollided()) {
                asteroidSplitter.createSplitAsteroid(asteroid, world);
                asteroid.setCollided(false);
            }
            
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * 0.5);
            asteroid.setY(asteroid.getY() + changeY * 0.5);

        }

    }

    @Override
    public Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        int size = 15;
        Random rnd = new Random();
        int sizeDeterminationVariable = rnd.nextInt(3);
        switch (sizeDeterminationVariable) {
            case 0:
                asteroid.setType(EntityType.SMALL_ASTEROID);
                size = 5;
            case 1:
                asteroid.setType(EntityType.MEDIUM_ASTEROID);
                size = 10;
            case 2:
                asteroid.setType(EntityType.LARGE_ASTEROID);
                size = 15;
            default:
                asteroid.setType(EntityType.LARGE_ASTEROID);
        }
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(0);
        asteroid.setY(0);
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        setSpawnPointAndRotation(gameData, asteroid);
        return asteroid;
    }

    public void setSpawnPointAndRotation(GameData gameData, Entity asteroid) {
        double xBound = gameData.getDisplayWidth();
        double yBound = gameData.getDisplayHeight();

        double xCoord = Math.random()*xBound;
        double yCoord = Math.random()*yBound;

        if (Math.random() > 0.5) {
            //Top
            if (xCoord > yCoord) {
                asteroid.setX(xCoord);
                asteroid.setY(-10);
                asteroid.setRotation((float) (Math.random()*180));
            }

            //Left
            if (yCoord > xCoord) {
                asteroid.setX(-10);
                asteroid.setY(yCoord);
                asteroid.setRotation((float) (90-(Math.random()*180)));
            }
        }
        else {
            //Bottom
            if (xCoord > yCoord) {
                asteroid.setX(xCoord);
                asteroid.setY(yBound+10);
                asteroid.setRotation((float) (180+(Math.random()*180)));
            }

            //Right
            if (yCoord > xCoord) {
                asteroid.setX(xBound+10);
                asteroid.setY(yCoord);
                asteroid.setRotation((float) (270-(Math.random()*180)));
            }
        }
    }

    public void handleAsteroidCreation(GameData gameData, World world) {
        long asteroidCount = world.getEntities(Asteroid.class).size();
        if (asteroidCount < 30) {
            getAsteroidSPIs().stream().findFirst().ifPresent(
                    spi -> {
                        world.addEntity(spi.createAsteroid(gameData));
                    }
            );
        }
    }

    private Collection<? extends AsteroidSPI> getAsteroidSPIs() {
        return ServiceLoader.load(AsteroidSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

}
