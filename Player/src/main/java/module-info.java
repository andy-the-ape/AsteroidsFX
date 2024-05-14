import dk.sdu.mmmi.cbse.common.player.PlayerSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Player {
    requires Common;
    requires CommonBullet;
    requires CommonPlayer;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides PlayerSPI with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
    provides IGamePluginService with dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
    
}
