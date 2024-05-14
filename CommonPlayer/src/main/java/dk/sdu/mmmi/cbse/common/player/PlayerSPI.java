package dk.sdu.mmmi.cbse.common.player;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface PlayerSPI {
    void resetPlayer(GameData gameData, World world);
}
