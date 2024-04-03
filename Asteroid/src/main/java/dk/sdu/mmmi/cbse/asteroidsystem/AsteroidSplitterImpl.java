package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        EntityType asteroidSize = e.getType();
        int size;
        switch (asteroidSize) {
            case LARGE_ASTEROID:
                e.setType(EntityType.MEDIUM_ASTEROID);
                size = 10;
                e.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
                e.setRadius(size);
            case MEDIUM_ASTEROID:
                e.setType(EntityType.SMALL_ASTEROID);
                size = 5;
                e.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
                e.setRadius(size);
            case SMALL_ASTEROID:
                world.removeEntity(e);
            default:
                world.removeEntity(e);
        }
    }

}