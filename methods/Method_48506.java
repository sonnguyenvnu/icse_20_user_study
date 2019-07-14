public void assignIDs(Iterable<InternalRelation> addedRelations){
  if (!placementStrategy.supportsBulkPlacement()) {
    for (    InternalRelation relation : addedRelations) {
      for (int i=0; i < relation.getArity(); i++) {
        InternalVertex vertex=relation.getVertex(i);
        if (!vertex.hasId()) {
          assignID(vertex,getVertexIDType(vertex));
        }
      }
      assignID(relation);
    }
  }
 else {
    Map<InternalVertex,PartitionAssignment> assignments=new HashMap<>();
    for (    InternalRelation relation : addedRelations) {
      for (int i=0; i < relation.getArity(); i++) {
        InternalVertex vertex=relation.getVertex(i);
        if (!vertex.hasId()) {
          assert !(vertex instanceof JanusGraphSchemaVertex);
          if (vertex.vertexLabel().isPartitioned())           assignID(vertex,getVertexIDType(vertex));
 else           assignments.put(vertex,PartitionAssignment.EMPTY);
        }
      }
    }
    log.trace("Bulk id assignment for {} vertices",assignments.size());
    for (int attempt=0; attempt < MAX_PARTITION_RENEW_ATTEMPTS && (assignments != null && !assignments.isEmpty()); attempt++) {
      placementStrategy.getPartitions(assignments);
      Map<InternalVertex,PartitionAssignment> leftOvers=null;
      Iterator<Map.Entry<InternalVertex,PartitionAssignment>> iterator=assignments.entrySet().iterator();
      while (iterator.hasNext()) {
        Map.Entry<InternalVertex,PartitionAssignment> entry=iterator.next();
        try {
          assignID(entry.getKey(),entry.getValue().getPartitionID(),getVertexIDType(entry.getKey()));
          Preconditions.checkArgument(entry.getKey().hasId());
        }
 catch (        IDPoolExhaustedException e) {
          if (leftOvers == null)           leftOvers=new HashMap<>();
          leftOvers.put(entry.getKey(),PartitionAssignment.EMPTY);
          break;
        }
      }
      if (leftOvers != null) {
        while (iterator.hasNext())         leftOvers.put(iterator.next().getKey(),PartitionAssignment.EMPTY);
        log.debug("Exhausted ID Pool in bulk assignment. Left-over vertices {}",leftOvers.size());
      }
      assignments=leftOvers;
    }
    if (assignments != null && !assignments.isEmpty())     throw new IDPoolExhaustedException("Could not find non-exhausted partition ID Pool after " + MAX_PARTITION_RENEW_ATTEMPTS + " attempts");
    for (    InternalRelation relation : addedRelations) {
      assignID(relation);
    }
  }
}
