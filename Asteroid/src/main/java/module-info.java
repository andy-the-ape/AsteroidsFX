import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

module Asteroid {
    requires Common;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
}