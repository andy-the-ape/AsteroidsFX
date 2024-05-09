package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author corfixen
 */
public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}
