package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;

public interface AsteroidSPI {
    void splitAsteroid(Entity asteroid, World world);
}
