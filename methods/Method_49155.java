private Iterable<InternalVertex> getInternalVertices(){
  Iterable<InternalVertex> allVertices;
  if (!addedRelations.isEmpty()) {
    List<InternalVertex> newVs=vertexCache.getAllNew();
    newVs.removeIf(internalVertex -> internalVertex instanceof JanusGraphSchemaElement);
    allVertices=Iterables.concat(newVs,new VertexIterable(graph,this));
  }
 else {
    allVertices=new VertexIterable(graph,this);
  }
  return Iterables.filter(allVertices,internalVertex -> !isPartitionedVertex(internalVertex) || internalVertex.longId() == idInspector.getCanonicalVertexId(internalVertex.longId()));
}
