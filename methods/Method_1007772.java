@Override public float noise(Vector2 position){
  return forceRange(module.GetValue(position.getX(),0,position.getZ()));
}
