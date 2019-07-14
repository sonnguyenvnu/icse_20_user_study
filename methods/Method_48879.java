@Override public boolean evaluate(E relation){
  return relation.isEdge() && ((JanusGraphEdge)relation).otherVertex(baseVertex).equals(otherVertex);
}
