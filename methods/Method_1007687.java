@Override public boolean test(BlockVector2 vector){
  if (masks.isEmpty()) {
    return false;
  }
  for (  Mask2D mask : masks) {
    if (!mask.test(vector)) {
      return false;
    }
  }
  return true;
}
