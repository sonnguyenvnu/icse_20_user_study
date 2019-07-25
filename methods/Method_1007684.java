@Override public boolean test(BlockVector3 vector){
  return vector.getY() >= minY && vector.getY() <= maxY;
}
