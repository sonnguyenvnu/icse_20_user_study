@Override public boolean support(String dimension){
  return DIMENSION_DEPARTMENT.equals(dimension) && userService != null;
}
