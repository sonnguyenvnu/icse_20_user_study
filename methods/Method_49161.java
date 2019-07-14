private void connectRelation(InternalRelation r){
  for (int i=0; i < r.getLen(); i++) {
    boolean success=r.getVertex(i).addRelation(r);
    if (!success)     throw new AssertionError("Could not connect relation: " + r);
  }
  addedRelations.add(r);
  for (int pos=0; pos < r.getLen(); pos++)   vertexCache.add(r.getVertex(pos),r.getVertex(pos).longId());
  if (TypeUtil.hasSimpleInternalVertexKeyIndex(r))   newVertexIndexEntries.add((JanusGraphVertexProperty)r);
}
