package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        double xBound = gameData.getDisplayWidth();
        double yBound = gameData.getDisplayHeight();

        for (Entity bullet : world.getEntities(Bullet.class)) {
            // Moves all bullets
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
            bullet.setX(bullet.getX() + changeX * bullet.getSpeed());
            bullet.setY(bullet.getY() + changeY * bullet.getSpeed());

            // Removes bullets that are out of bounds
            if (
                    (bullet.getX() > xBound)
                    || (bullet.getX() < 0)
                    || (bullet.getY() > yBound)
                    || (bullet.getY() < 0)
            ) {
                world.removeEntity(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet(shooter);
        bullet.setRotation(shooter.getRotation());
        bullet.setCollisionBoxRadius(1);
        return bullet;
    }
}
