import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    uses dk.sdu.mmmi.cbse.common.player.PlayerSPI;
    uses dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
    uses dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
    requires Common;
    requires CommonBullet;
    requires CommonPlayer;
    requires CommonEnemy;
    requires CommonAsteroid;
    requires java.net.http;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
}