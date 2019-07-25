@Override public float noise(Vector3 position){
  return forceRange(module.GetValue(position.getX(),position.getY(),position.getZ()));
}
