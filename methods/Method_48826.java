@Override public Direction direction(Vertex vertex){
  if (isIncidentOn(vertex))   return Direction.OUT;
  throw new IllegalArgumentException("Property is not incident on vertex");
}
