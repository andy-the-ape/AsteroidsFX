package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.player.Player;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Collection;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity player : world.getEntities(Player.class)) {
            // Controlling player movement and shooting
            if (gameData.getKeys().isDown(GameKeys.A)) {
                player.setRotation(player.getRotation() - player.getRotationSpeed());
            }
            if (gameData.getKeys().isDown(GameKeys.D)) {
                player.setRotation(player.getRotation() + player.getRotationSpeed());
            }
            if (gameData.getKeys().isDown(GameKeys.W)) {
                double changeX = Math.cos(Math.toRadians(player.getRotation()));
                double changeY = Math.sin(Math.toRadians(player.getRotation()));
                player.setX(player.getX() + changeX * player.getSpeed());
                player.setY(player.getY() + changeY * player.getSpeed());
            }
            if(gameData.getKeys().isPressed(GameKeys.SPACE)) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(player, gameData));}
                );
            }

            // Keeping player in bounds
            if (player.getX() < 0) {
                player.setX(1);
            }
            if (player.getX() > gameData.getDisplayWidth()) {
                player.setX(gameData.getDisplayWidth()-1);
            }
            if (player.getY() < 0) {
                player.setY(1);
            }
            if (player.getY() > gameData.getDisplayHeight()) {
                player.setY(gameData.getDisplayHeight()-1);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
