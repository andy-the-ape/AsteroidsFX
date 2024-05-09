package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.entities.EntityColor;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService, EnemySPI {

    @Override
    public void process(GameData gameData, World world) {
        handleTimeTick(gameData, world);

        double xBound = gameData.getDisplayWidth()+50;
        double yBound = gameData.getDisplayHeight()+50;

        for (Entity enemy : world.getEntities(Enemy.class)) {
            double xCoord = enemy.getX();
            double yCoord = enemy.getY();

            // Arena collision
            if (enemy.getX() >= xBound || enemy.getX() <= -50) {
                enemy.setRotation(enemy.getRotation() + 180);
            }
            if (enemy.getY() >= yBound || enemy.getY() <= -50) {
                enemy.setRotation(enemy.getRotation() + 180);
            }

            if (xCoord < xBound && xCoord > -50 &&
                    yCoord < yBound && yCoord > -50) {

                double randDirection = Math.random();
                double randTriggerHappiness = Math.random();

                if (randDirection >= 0.7) {
                    enemy.setRotation(enemy.getRotation() + 1);
                } else if (randDirection <= 0.3) {
                    enemy.setRotation(enemy.getRotation() - 1);
                }
                if (randTriggerHappiness > 0.999) {
                    getBulletSPIs().stream().findFirst().ifPresent(
                            spi -> {world.addEntity(spi.createBullet(enemy, gameData));}
                    );
                }

                double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                enemy.setX(enemy.getX() + changeX * 0.5);
                enemy.setY(enemy.getY() + changeY * 0.5);
            }
            else {
                world.removeEntity(enemy);
            }
        }
    }

    @Override
    public Entity createEnemy(GameData gameData) {
        Enemy enemy = new Enemy();
        setSpawnPointAndRotation(gameData, enemy);
        return enemy;
    }

    public void setSpawnPointAndRotation(GameData gameData, Entity enemy) {
        double xBound = gameData.getDisplayWidth();
        double yBound = gameData.getDisplayHeight();

        double xCoord = Math.random()*xBound;
        double yCoord = Math.random()*yBound;

        if (Math.random() > 0.5) {
            //Top
            if (xCoord > yCoord) {
                enemy.setX(xCoord);
                enemy.setY(-10);
                enemy.setRotation((float) (Math.random()*180));
            }

            //Left
            if (yCoord > xCoord) {
                enemy.setX(-10);
                enemy.setY(yCoord);
                enemy.setRotation((float) (90-(Math.random()*180)));
            }
        }
        else {
            //Bottom
            if (xCoord > yCoord) {
                enemy.setX(xCoord);
                enemy.setY(yBound+10);
                enemy.setRotation((float) (180+(Math.random()*180)));
            }

            //Right
            if (yCoord > xCoord) {
                enemy.setX(xBound+10);
                enemy.setY(yCoord);
                enemy.setRotation((float) (270-(Math.random()*180)));
            }
        }
    }

    public void handleTimeTick(GameData gameData, World world) {
        if (gameData.getTimeTick() == 0) {
            gameData.setTimeTick(System.currentTimeMillis());
        }
        else {
            long oldTime = gameData.getTimeTick();
            long newTime = System.currentTimeMillis();
            if (newTime > oldTime+10000) {
                handleEnemyCreation(gameData, world);
                gameData.setTimeTick(System.currentTimeMillis());
            }
        }
    }

    public void handleEnemyCreation(GameData gameData, World world) {
        long enemyCount = world.getEntities(Enemy.class).size();
        if (enemyCount < 4) {
            getEnemySPIs().stream().findFirst().ifPresent(
                    spi -> {
                        world.addEntity(spi.createEnemy(gameData));
                    }
            );
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends EnemySPI> getEnemySPIs() {
        return ServiceLoader.load(EnemySPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
