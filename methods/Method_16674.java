@Override public boolean support(String dimension){
  return DIMENSION_ORG.equals(dimension) && userService != null;
}
