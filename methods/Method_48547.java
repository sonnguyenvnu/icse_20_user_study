public Collection<IndexUpdate> getIndexUpdates(InternalVertex vertex,Collection<InternalRelation> updatedProperties){
  if (updatedProperties.isEmpty())   return Collections.emptyList();
  final Set<IndexUpdate> updates=Sets.newHashSet();
  for (  final InternalRelation rel : updatedProperties) {
    assert rel.isProperty();
    final JanusGraphVertexProperty p=(JanusGraphVertexProperty)rel;
    assert rel.isNew() || rel.isRemoved();
    assert rel.getVertex(0).equals(vertex);
    final IndexUpdate.Type updateType=getUpdateType(rel);
    for (    final IndexType index : ((InternalRelationType)p.propertyKey()).getKeyIndexes()) {
      if (!indexAppliesTo(index,vertex))       continue;
      if (index.isCompositeIndex()) {
        final CompositeIndexType cIndex=(CompositeIndexType)index;
        final IndexRecords updateRecords=indexMatches(vertex,cIndex,updateType == IndexUpdate.Type.DELETE,p.propertyKey(),new RecordEntry(p));
        for (        final RecordEntry[] record : updateRecords) {
          final IndexUpdate update=new IndexUpdate<>(cIndex,updateType,getIndexKey(cIndex,record),getIndexEntry(cIndex,record,vertex),vertex);
          final int ttl=getIndexTTL(vertex,getKeysOfRecords(record));
          if (ttl > 0 && updateType == IndexUpdate.Type.ADD)           update.setTTL(ttl);
          updates.add(update);
        }
      }
 else {
        if (((MixedIndexType)index).getField(p.propertyKey()).getStatus() == SchemaStatus.DISABLED)         continue;
        final IndexUpdate update=getMixedIndexUpdate(vertex,p.propertyKey(),p.value(),(MixedIndexType)index,updateType);
        final int ttl=getIndexTTL(vertex,p.propertyKey());
        if (ttl > 0 && updateType == IndexUpdate.Type.ADD)         update.setTTL(ttl);
        updates.add(update);
      }
    }
  }
  return updates;
}
