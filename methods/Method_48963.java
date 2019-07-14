public VertexList executeVertexIds(InternalVertex vertex,BaseVertexCentricQuery baseQuery){
  if (isPartitionedVertex(vertex)) {
    if (!orders.isEmpty())     return edges2VertexIds((Iterable)executeRelations(vertex,baseQuery),vertex);
    if (!hasAllCanonicalTypes()) {
      InternalVertex[] representatives=tx.getAllRepresentatives(vertex,restrict2Partitions);
      VertexListInternal merge=null;
      for (      InternalVertex rep : representatives) {
        if (merge != null && merge.size() >= baseQuery.getLimit())         break;
        VertexList vertexList=executeIndividualVertexIds(rep,baseQuery);
        if (merge == null)         merge=(VertexListInternal)vertexList;
 else         merge.addAll(vertexList);
      }
      if (merge != null && merge.size() > baseQuery.getLimit()) {
        merge=(VertexListInternal)merge.subList(0,baseQuery.getLimit());
      }
      return merge;
    }
 else     vertex=tx.getCanonicalVertex(vertex);
  }
  return executeIndividualVertexIds(vertex,baseQuery);
}
