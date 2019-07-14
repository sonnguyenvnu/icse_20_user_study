@Override public boolean evaluate(E element){
  if (direction == Direction.BOTH)   return true;
  if (element instanceof CacheEdge) {
    return direction == ((CacheEdge)element).getVertexCentricDirection();
  }
 else   if (element instanceof JanusGraphEdge) {
    return ((JanusGraphEdge)element).vertex(direction).equals(baseVertex);
  }
 else   if (element instanceof JanusGraphVertexProperty) {
    return direction == Direction.OUT;
  }
  return false;
}
