package dk.sdu.mmmi.cbse.common.player;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;

public interface PlayerSPI {
    void resetPlayer(Entity enemy, GameData gameData);
}
