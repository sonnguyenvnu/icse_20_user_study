@Override public Iterable<JanusGraphVertex> vertices(){
  return iterables(constructQuery(ElementCategory.VERTEX),JanusGraphVertex.class);
}
