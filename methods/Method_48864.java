@Override public Predicate<StaticBuffer> getKeyFilter(){
  return buffer -> !IDManager.VertexIDType.Invisible.is(getVertexId(buffer));
}
