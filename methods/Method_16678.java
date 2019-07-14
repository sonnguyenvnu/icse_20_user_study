@Override public boolean support(String dimension){
  return DIMENSION_ROLE.equals(dimension) && userService != null;
}
