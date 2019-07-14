public TinkerVertex readHadoopVertex(final StaticBuffer key,Iterable<Entry> entries){
  final long vertexId=idManager.getKeyID(key);
  Preconditions.checkArgument(vertexId > 0);
  if (idManager.isPartitionedVertex(vertexId)) {
    Preconditions.checkState(setup.getFilterPartitionedVertices(),"Read partitioned vertex (ID=%s), but partitioned vertex filtering is disabled.",vertexId);
    log.debug("Skipping partitioned vertex with ID {}",vertexId);
    return null;
  }
  TinkerGraph tg=TinkerGraph.open();
  TinkerVertex tv=null;
  for (  final Entry data : entries) {
    RelationReader relationReader=setup.getRelationReader(vertexId);
    final RelationCache relation=relationReader.parseRelation(data,false,typeManager);
    if (systemTypes.isVertexLabelSystemType(relation.typeId)) {
      long vertexLabelId=relation.getOtherVertexId();
      VertexLabel vl=typeManager.getExistingVertexLabel(vertexLabelId);
      tv=getOrCreateVertex(vertexId,vl.name(),tg);
    }
  }
  if (null == tv) {
    tv=getOrCreateVertex(vertexId,null,tg);
  }
  Preconditions.checkNotNull(tv,"Unable to determine vertex label for vertex with ID %s",vertexId);
  for (  final Entry data : entries) {
    try {
      RelationReader relationReader=setup.getRelationReader(vertexId);
      final RelationCache relation=relationReader.parseRelation(data,false,typeManager);
      if (systemTypes.isSystemType(relation.typeId))       continue;
      final RelationType type=typeManager.getExistingRelationType(relation.typeId);
      if (((InternalRelationType)type).isInvisibleType())       continue;
      if (type.isPropertyKey()) {
        Object value=relation.getValue();
        Preconditions.checkNotNull(value);
        VertexProperty.Cardinality card=getPropertyKeyCardinality(type.name());
        tv.property(card,type.name(),value,T.id,relation.relationId);
      }
 else {
        assert type.isEdgeLabel();
        if (idManager.isPartitionedVertex(relation.getOtherVertexId())) {
          Preconditions.checkState(setup.getFilterPartitionedVertices(),"Read edge incident on a partitioned vertex, but partitioned vertex filtering is disabled.  " + "Relation ID: %s.  This vertex ID: %s.  Other vertex ID: %s.  Edge label: %s.",relation.relationId,vertexId,relation.getOtherVertexId(),type.name());
          log.debug("Skipping edge with ID {} incident on partitioned vertex with ID {} (and nonpartitioned vertex with ID {})",relation.relationId,relation.getOtherVertexId(),vertexId);
          continue;
        }
        TinkerEdge te;
        TinkerVertex adjacentVertex=getOrCreateVertex(relation.getOtherVertexId(),null,tg);
        if (tv.equals(adjacentVertex) && isLoopAdded(tv,type.name())) {
          continue;
        }
        if (relation.direction.equals(Direction.IN)) {
          te=(TinkerEdge)adjacentVertex.addEdge(type.name(),tv,T.id,relation.relationId);
        }
 else         if (relation.direction.equals(Direction.OUT)) {
          te=(TinkerEdge)tv.addEdge(type.name(),adjacentVertex,T.id,relation.relationId);
        }
 else {
          throw new RuntimeException("Direction.BOTH is not supported");
        }
        if (relation.hasProperties()) {
          for (          final LongObjectCursor<Object> next : relation) {
            assert next.value != null;
            RelationType rt=typeManager.getExistingRelationType(next.key);
            if (rt.isPropertyKey()) {
              te.property(rt.name(),next.value);
            }
 else {
              throw new RuntimeException("Metaedges are not supported");
            }
          }
        }
      }
    }
 catch (    Exception e) {
      throw new RuntimeException(e);
    }
  }
  if (!tv.edges(Direction.BOTH).hasNext() && !tv.properties().hasNext()) {
    log.trace("Vertex {} has no relations",vertexId);
    return null;
  }
  return tv;
}
