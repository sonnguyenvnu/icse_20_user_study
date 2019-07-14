@Override public IndexJobFuture updateIndex(Index index,SchemaAction updateAction){
  Preconditions.checkArgument(index != null,"Need to provide an index");
  Preconditions.checkArgument(updateAction != null,"Need to provide update action");
  JanusGraphSchemaVertex schemaVertex=getSchemaVertex(index);
  Set<JanusGraphSchemaVertex> dependentTypes;
  Set<PropertyKeyVertex> keySubset=ImmutableSet.of();
  if (index instanceof RelationTypeIndex) {
    dependentTypes=ImmutableSet.of((JanusGraphSchemaVertex)((InternalRelationType)schemaVertex).getBaseType());
    if (!updateAction.isApplicableStatus(schemaVertex.getStatus()))     return null;
  }
 else   if (index instanceof JanusGraphIndex) {
    IndexType indexType=schemaVertex.asIndexType();
    dependentTypes=Sets.newHashSet();
    if (indexType.isCompositeIndex()) {
      if (!updateAction.isApplicableStatus(schemaVertex.getStatus()))       return null;
      for (      PropertyKey key : ((JanusGraphIndex)index).getFieldKeys()) {
        dependentTypes.add((PropertyKeyVertex)key);
      }
    }
 else {
      keySubset=Sets.newHashSet();
      MixedIndexType mixedIndexType=(MixedIndexType)indexType;
      Set<SchemaStatus> applicableStatus=updateAction.getApplicableStatus();
      for (      ParameterIndexField field : mixedIndexType.getFieldKeys()) {
        if (applicableStatus.contains(field.getStatus()))         keySubset.add((PropertyKeyVertex)field.getFieldKey());
      }
      if (keySubset.isEmpty())       return null;
      dependentTypes.addAll(keySubset);
    }
  }
 else   throw new UnsupportedOperationException("Updates not supported for index: " + index);
  IndexIdentifier indexId=new IndexIdentifier(index);
  StandardScanner.Builder builder;
  IndexJobFuture future;
switch (updateAction) {
case REGISTER_INDEX:
    setStatus(schemaVertex,SchemaStatus.INSTALLED,keySubset);
  updatedTypes.add(schemaVertex);
updatedTypes.addAll(dependentTypes);
setUpdateTrigger(new UpdateStatusTrigger(graph,schemaVertex,SchemaStatus.REGISTERED,keySubset));
future=new EmptyIndexJobFuture();
break;
case REINDEX:
builder=graph.getBackend().buildEdgeScanJob();
builder.setFinishJob(indexId.getIndexJobFinisher(graph,SchemaAction.ENABLE_INDEX));
builder.setJobId(indexId);
builder.setJob(VertexJobConverter.convert(graph,new IndexRepairJob(indexId.indexName,indexId.relationTypeName)));
try {
future=builder.execute();
}
 catch (BackendException e) {
throw new JanusGraphException(e);
}
break;
case ENABLE_INDEX:
setStatus(schemaVertex,SchemaStatus.ENABLED,keySubset);
updatedTypes.add(schemaVertex);
if (!keySubset.isEmpty()) updatedTypes.addAll(dependentTypes);
future=new EmptyIndexJobFuture();
break;
case DISABLE_INDEX:
setStatus(schemaVertex,SchemaStatus.INSTALLED,keySubset);
updatedTypes.add(schemaVertex);
if (!keySubset.isEmpty()) updatedTypes.addAll(dependentTypes);
setUpdateTrigger(new UpdateStatusTrigger(graph,schemaVertex,SchemaStatus.DISABLED,keySubset));
future=new EmptyIndexJobFuture();
break;
case REMOVE_INDEX:
if (index instanceof RelationTypeIndex) {
builder=graph.getBackend().buildEdgeScanJob();
}
 else {
JanusGraphIndex graphIndex=(JanusGraphIndex)index;
if (graphIndex.isMixedIndex()) throw new UnsupportedOperationException("External mixed indexes must be removed in the indexing system directly.");
builder=graph.getBackend().buildGraphIndexScanJob();
}
builder.setFinishJob(indexId.getIndexJobFinisher());
builder.setJobId(indexId);
builder.setJob(new IndexRemoveJob(graph,indexId.indexName,indexId.relationTypeName));
try {
future=builder.execute();
}
 catch (BackendException e) {
throw new JanusGraphException(e);
}
break;
default :
throw new UnsupportedOperationException("Update action not supported: " + updateAction);
}
return future;
}
