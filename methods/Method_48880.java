@Override public boolean evaluate(E relation){
  return relation.isEdge() && ((JanusGraphEdge)relation).vertex(direction).equals(otherVertex);
}
