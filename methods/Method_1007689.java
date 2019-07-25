@Override public boolean test(BlockVector2 vector){
  Collection<Mask2D> masks=getMasks();
  for (  Mask2D mask : masks) {
    if (mask.test(vector)) {
      return true;
    }
  }
  return false;
}
