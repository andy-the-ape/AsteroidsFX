package dk.sdu.mmmi.cbse.common.enemy;

import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public interface EnemySPI {
    Entity createEnemy(GameData gameData);
}