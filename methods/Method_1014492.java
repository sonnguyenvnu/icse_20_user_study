/** 
 * Called when an object attempts to walk directly into a Enterable object
 * @param object The game object that attempted to enter
 * @return true if successful, false to block the object
 */
@Override public boolean enter(GameObject object){
  World world=GameServer.INSTANCE.getGameUniverse().getWorld(destination.worldX,destination.worldY,false,destination.dimension);
  if (object instanceof Updatable) {
    object.getWorld().decUpdatable();
    world.incUpdatable();
  }
  object.getWorld().removeObject(object);
  object.setWorld(world);
  world.addObject(object);
  object.setX(destination.x);
  object.setY(destination.y);
  return true;
}
