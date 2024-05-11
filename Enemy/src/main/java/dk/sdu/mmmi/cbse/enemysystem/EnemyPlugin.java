package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class EnemyPlugin implements IGamePluginService, EnemySPI {

    private Entity enemy;
    public EnemyPlugin() {}

    @Override
    public void start(GameData gameData, World world) {
        // Add Enemy entity to the world
        enemy = createEnemy(gameData);
        world.addEntity(enemy);
    }

    @Override
    public void resetEnemy(Entity enemy, GameData gameData) {
        setSpawnCoordinatesAndRotation(enemy, gameData);
    }

    @Override
    public Entity createEnemy(GameData gameData) {
        enemy = new Enemy();
        setSpawnCoordinatesAndRotation(enemy, gameData);
        return enemy;
    }

    public void setSpawnCoordinatesAndRotation(Entity enemy, GameData gameData) {
        Random random = new Random();
        switch (random.nextInt(4)) {
            case 0:
                enemy.setX(0);
                enemy.setY(0);
                pointEnemyAtTheCenterOfTheScreen(gameData, enemy);
                break;
            case 1:
                enemy.setX(gameData.getDisplayWidth());
                enemy.setY(0);
                pointEnemyAtTheCenterOfTheScreen(gameData, enemy);
                break;
            case 2:
                enemy.setX(0);
                enemy.setY(gameData.getDisplayHeight());
                pointEnemyAtTheCenterOfTheScreen(gameData, enemy);
                break;
            case 3:
                enemy.setX(gameData.getDisplayWidth());
                enemy.setY(gameData.getDisplayHeight());
                pointEnemyAtTheCenterOfTheScreen(gameData, enemy);
                break;
        }
    }

    public void pointEnemyAtTheCenterOfTheScreen(GameData gameData, Entity enemy) {
        double xCenter = (double) gameData.getDisplayWidth() /2;
        double yCenter = (double) gameData.getDisplayHeight()/2;
        double angleDeg = Math.toDegrees(Math.atan2(yCenter - enemy.getY(), xCenter - enemy.getX()));
        if (angleDeg < 0) {
            angleDeg += 360;
        }
        enemy.setRotation(angleDeg);
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove all Enemy entities
        for (Entity e : world.getEntities(Enemy.class)) {
            world.removeEntity(e);
            System.out.println("Removed an enemy ship!");
        }
    }

}