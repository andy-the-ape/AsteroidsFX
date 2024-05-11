package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {
    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        double xBound = gameData.getDisplayWidth();
        double yBound = gameData.getDisplayHeight();

        for (Entity enemy : world.getEntities(Enemy.class)) {

            //Turning randomly
            int randomDirectionModifier = random.nextInt(101);

            if (randomDirectionModifier > 70) {
                enemy.setRotation(enemy.getRotation() + 2);
            } else if (randomDirectionModifier < 30) {
                enemy.setRotation(enemy.getRotation() - 2);
            }

            //Shooting randomly
            int randomTriggerHappiness = random.nextInt(1001);
            if (randomTriggerHappiness > 997) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
                );
            }

            //Movement
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX * enemy.getSpeed());
            enemy.setY(enemy.getY() + changeY * enemy.getSpeed());

            //Turn to point towards center on Arena collision
            if (enemy.getX() >= xBound || enemy.getX() <= 0 || enemy.getY() >= yBound || enemy.getY() <= 0) {
                double angleDeg = Math.toDegrees(Math.atan2(yBound/2 - enemy.getY(), xBound/2 - enemy.getX()));
                if (angleDeg < 0) {
                    angleDeg += 360;
                }
                enemy.setRotation(angleDeg);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
