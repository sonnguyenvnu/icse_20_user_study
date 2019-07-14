@Override public JanusGraphVertex otherVertex(Vertex vertex){
  if (start.equals(vertex))   return end;
  if (end.equals(vertex))   return start;
  throw new IllegalArgumentException("Edge is not incident on vertex");
}
