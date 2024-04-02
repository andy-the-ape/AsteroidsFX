package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService, AsteroidSPI {

    private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * 0.5);
            asteroid.setY(asteroid.getY() + changeY * 0.5);

            if (asteroid.getX() < 0) {
                asteroid.setX(asteroid.getX() - gameData.getDisplayWidth());
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(asteroid.getY() - gameData.getDisplayHeight());
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
            }

        }

    }

    /**
     * Dependency Injection using OSGi Declarative Services
     */
    public void setAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = asteroidSplitter;
    }

    public void removeAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = null;
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
        return asteroid;
    }


}
