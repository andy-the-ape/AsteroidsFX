package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface AsteroidSPI {
    Entity createAsteroid(GameData gameData);

    void createSplitAsteroid(Entity e, World w);
}