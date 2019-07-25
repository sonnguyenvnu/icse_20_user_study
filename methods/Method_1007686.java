@Override public boolean test(BlockVector3 vector){
  if (masks.isEmpty()) {
    return false;
  }
  for (  Mask mask : masks) {
    if (!mask.test(vector)) {
      return false;
    }
  }
  return true;
}
