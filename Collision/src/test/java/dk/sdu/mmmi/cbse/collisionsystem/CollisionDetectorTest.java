package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CollisionDetectorTest {

    private CollisionDetector collisionDetector;
    private Entity player;
    private Entity asteroid;

    @Mock
    private World worldMock = new World();

    @Mock
    private GameData gameDataMock = new GameData();

    @Before
    public void setUp() {
        collisionDetector = new CollisionDetector();
        player = new Player();
        asteroid = new Asteroid(0.2, 1.8);
        worldMock.addEntity(player);
        worldMock.addEntity(asteroid);
    }

    @After
    public void tearDown() {
        worldMock.removeEntity(player);
        worldMock.removeEntity(asteroid);
    }

    @Test
    public void playerAndAsteroidCollisionResetsPlayerXCoord() {
        collisionDetector.handleCollision(player, asteroid, gameDataMock, worldMock);

        assertEquals((double) gameDataMock.getDisplayWidth() /2, player.getX(), 0.1);
    }
    @Test
    public void playerAndAsteroidCollisionResetsPlayerYCoord() {
        collisionDetector.handleCollision(player, asteroid, gameDataMock, worldMock);

        assertEquals((double) gameDataMock.getDisplayHeight() /2, player.getY(), 0.1);
    }
    @Test
    public void playerAndAsteroidCollisionRemovesAsteroid() {
        collisionDetector.handleCollision(player, asteroid, gameDataMock, worldMock);

        // Verify that the asteroid is removed
        assert worldMock.getEntity(asteroid.getID()) == null;
    }
}