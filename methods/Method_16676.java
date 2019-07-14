@Override public boolean support(String dimension){
  return DIMENSION_POSITION.equals(dimension) && userService != null;
}
