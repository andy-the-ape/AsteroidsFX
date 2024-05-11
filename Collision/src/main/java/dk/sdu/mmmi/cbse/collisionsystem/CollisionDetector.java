package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.data.entities.Entity;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {

        //Iterating over all entities and for each entity, check if it collides with any other entity
        for (Entity entity : world.getEntities()) {
            for (Entity otherEntity : world.getEntities()) {
                //Skip if the entity is the same as the other entity
                if (entity.getID().equals(otherEntity.getID())) {
                    continue;
                }
                //Only check for collision if the entities are collidable


            }
        }
    }

}
