package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

/**
 *
 * @author corfixen
 */
public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        Entity asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        int size = rnd.nextInt(10) + 5;
        Random rnd = new Random();
        int sizeDeterminationVariable = rnd.nextInt(3);
        switch (sizeDeterminationVariable) {
            case 0: asteroid.setType(EntityType.SMALL_ASTEROID);
            case 1: asteroid.setType(EntityType.MEDIUM_ASTEROID);
            case 2: asteroid.setType(EntityType.LARGE_ASTEROID);
            default: asteroid.setType(EntityType.LARGE_ASTEROID);
        }

        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(0);
        asteroid.setY(0);
        asteroid.setRadius(size);
        asteroid.setRotation(rnd.nextInt(90));
        asteroid.setColor(EntityColor.GRAY);
        asteroid.setType(E);
        return asteroid;
    }
}
