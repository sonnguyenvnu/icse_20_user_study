@Override public boolean remove(){
  org.bukkit.entity.Entity entity=entityRef.get();
  if (entity != null) {
    entity.remove();
    return entity.isDead();
  }
 else {
    return true;
  }
}
