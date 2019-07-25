@Override public boolean enter(GameObject object){
  if (open) {
    object.getWorld().decUpdatable();
    object.getWorld().removeObject(object);
    homeWorld.incUpdatable();
    homeWorld.addObject(object);
    object.setWorld(homeWorld);
    object.setX(homeX);
    object.setY(homeY);
    return true;
  }
 else {
    return false;
  }
}
