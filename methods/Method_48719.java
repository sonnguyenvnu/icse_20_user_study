public static int getTTL(InternalVertex v){
  assert v.hasId();
  if (IDManager.VertexIDType.UnmodifiableVertex.is(v.longId())) {
    assert v.isNew() : "Should not be able to add relations to existing static vertices: " + v;
    return ((InternalVertexLabel)v.vertexLabel()).getTTL();
  }
 else   return 0;
}
