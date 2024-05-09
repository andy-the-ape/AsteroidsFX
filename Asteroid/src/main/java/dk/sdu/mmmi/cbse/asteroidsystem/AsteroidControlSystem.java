package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

    }

    public void createAsteroid() {
        Asteroid asteroid = new Asteroid(2,-8,0,-6,6,0,8,6,6,8,0,6,-6,0,-8,-6,-6);
    }

    public void createSplitAsteroid(Asteroid originalAsteroid) {
        Asteroid asteroid = new Asteroid(1,-5,0,-4,4,0,5,4,4,5,0,4,-4,0,-5,-4,-4);
        asteroid.setX(originalAsteroid.getX());
        asteroid.setY(originalAsteroid.getY());

    }

    public void setSpawnPointAndRotation(GameData gameData, Entity asteroid) {
        double xBound = gameData.getDisplayWidth();
        double yBound = gameData.getDisplayHeight();

        double xCoord = Math.random()*xBound;
        double yCoord = Math.random()*yBound;

        if (Math.random() > 0.5) {
            //Top
            if (xCoord > yCoord) {
                asteroid.setX(xCoord);
                asteroid.setY(-10);
                asteroid.setRotation((float) (Math.random()*180));
            }

            //Left
            if (yCoord > xCoord) {
                asteroid.setX(-10);
                asteroid.setY(yCoord);
                asteroid.setRotation((float) (90-(Math.random()*180)));
            }
        }
        else {
            //Bottom
            if (xCoord > yCoord) {
                asteroid.setX(xCoord);
                asteroid.setY(yBound+10);
                asteroid.setRotation((float) (180+(Math.random()*180)));
            }

            //Right
            if (yCoord > xCoord) {
                asteroid.setX(xBound+10);
                asteroid.setY(yCoord);
                asteroid.setRotation((float) (270-(Math.random()*180)));
            }
        }
    }

}
