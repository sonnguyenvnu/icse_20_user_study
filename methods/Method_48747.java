public boolean isUnmodifiableVertex(long id){
  return isUserVertexId(id) && VertexIDType.UnmodifiableVertex.is(id);
}
