import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidSPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Asteroid {
    exports dk.sdu.mmmi.cbse.asteroidsystem;
    requires Common;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
    provides AsteroidSPI with dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
}