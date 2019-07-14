@Override public boolean evaluate(E element){
  Preconditions.checkArgument(element instanceof JanusGraphRelation);
  return relationType.equals(((JanusGraphRelation)element).getType());
}
