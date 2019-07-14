protected Iterable<JanusGraphRelation> executeRelations(InternalVertex vertex,BaseVertexCentricQuery baseQuery){
  if (isPartitionedVertex(vertex)) {
    if (!hasAllCanonicalTypes()) {
      InternalVertex[] representatives=tx.getAllRepresentatives(vertex,restrict2Partitions);
      Iterable<JanusGraphRelation> merge=null;
      for (      InternalVertex rep : representatives) {
        Iterable<JanusGraphRelation> iterable=executeIndividualRelations(rep,baseQuery);
        if (merge == null) {
          merge=iterable;
        }
 else {
          merge=ResultMergeSortIterator.mergeSort(merge,iterable,(Comparator)orders,false);
        }
      }
      return ResultSetIterator.wrap(merge,baseQuery.getLimit());
    }
 else     vertex=tx.getCanonicalVertex(vertex);
  }
  return executeIndividualRelations(vertex,baseQuery);
}
