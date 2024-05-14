package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.entities.EntityType;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.player.PlayerSPI;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {

        //Iterating over all entities and for each entity, check if it collides with any other entity
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                //Skip if the entities are the same, or of the same type
                if (entity1.equals(entity2) || entity1.getType().equals(entity2.getType())) {
                    continue;
                }
                //Ignore checks between enemies and asteroids and vice versa
                if (entity1.getType().equals(EntityType.ENEMY)
                        && entity2.getType().equals(EntityType.ASTEROID)
                        || entity1.getType().equals(EntityType.ASTEROID)
                        && entity2.getType().equals(EntityType.ENEMY)
                ) {
                    continue;
                }
                //Ignore checks between bullet and shooter of the bullet
                if (entity1.getColor().equals(entity2.getColor())) {
                    continue;
                }

                //Skip if the entities are not colliding
                if (!isColliding(entity1, entity2)) {
                    continue;
                }
                //Handle collision
                handleCollision(entity1, entity2, gameData, world);
            }
        }
    }

    public void handleCollision(Entity entity1, Entity entity2, GameData gameData, World world) {

        //Bullet and Enemy collision
        if ((entity1.getType().equals(EntityType.BULLET) && entity2.getType().equals(EntityType.ENEMY))
                || (entity1.getType().equals(EntityType.ENEMY) && entity2.getType().equals(EntityType.BULLET))) {
            //Removing the bullet
            world.removeEntity(entity1.getType().equals(EntityType.BULLET) ? entity1 : entity2);
            //Resetting the enemy
            getEnemySPIs().stream().findFirst().ifPresent(
                    enemySPI -> {
                        enemySPI.resetEnemy(entity1.getType().equals(EntityType.ENEMY) ? entity1 : entity2, gameData);
                    }
            );
        }

        //Bullet and Player collision
        if ((entity1.getType().equals(EntityType.BULLET) && entity2.getType().equals(EntityType.PLAYER))
                || (entity1.getType().equals(EntityType.PLAYER) && entity2.getType().equals(EntityType.BULLET))) {
            //Removing the bullet
            world.removeEntity(entity1.getType().equals(EntityType.BULLET) ? entity1 : entity2);
            //Resetting the player
            System.out.println("Detected collision on player");
            getPlayerSPIs().stream().findFirst().ifPresent(
                    playerSPI -> {
                        playerSPI.resetPlayer(gameData, world);
                    }
            );
        }

        //Bullet and Asteroid collision
        if ((entity1.getType().equals(EntityType.BULLET) && entity2.getType().equals(EntityType.ASTEROID))
                || (entity1.getType().equals(EntityType.ASTEROID) && entity2.getType().equals(EntityType.BULLET))) {
            //Removing the bullet
            world.removeEntity(entity1.getType().equals(EntityType.BULLET) ? entity1 : entity2);
            //Splitting the asteroid
            if (entity1.getType().equals(EntityType.ASTEROID)) {
                getAsteroidSPIs().stream().findFirst().ifPresent(
                        asteroidSPI -> {
                            asteroidSPI.splitAsteroid(entity1, world);
                        }
                );
            } else if (entity2.getType().equals(EntityType.ASTEROID)) {
                getAsteroidSPIs().stream().findFirst().ifPresent(
                        asteroidSPI -> {
                            asteroidSPI.splitAsteroid(entity2, world);
                        }
                );
            }

        }
    }

    public boolean isColliding(Entity entity1, Entity entity2) {
        double distance = Math.sqrt(Math.pow(entity1.getX() - entity2.getX(),2) + Math.pow(entity1.getY() - entity2.getY(),2));
        return distance <= entity1.getCollisionBoxRadius() + entity2.getCollisionBoxRadius();
    }

    private Collection<? extends PlayerSPI> getPlayerSPIs() {
        return ServiceLoader.load(PlayerSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends EnemySPI> getEnemySPIs() {
        return ServiceLoader.load(EnemySPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends AsteroidSPI> getAsteroidSPIs() {
        return ServiceLoader.load(AsteroidSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
