import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;

module Enemy {
    uses EnemySPI;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    requires Common;
    requires CommonEnemy;
    requires CommonBullet;
    provides IGamePluginService with dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
    provides EnemySPI with dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
}