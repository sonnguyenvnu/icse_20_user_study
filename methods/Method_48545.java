public Collection<IndexUpdate> getIndexUpdates(InternalRelation relation){
  assert relation.isNew() || relation.isRemoved();
  final Set<IndexUpdate> updates=Sets.newHashSet();
  final IndexUpdate.Type updateType=getUpdateType(relation);
  final int ttl=updateType == IndexUpdate.Type.ADD ? StandardJanusGraph.getTTL(relation) : 0;
  for (  final RelationType type : relation.getPropertyKeysDirect()) {
    if (!(type instanceof PropertyKey))     continue;
    final PropertyKey key=(PropertyKey)type;
    for (    final IndexType index : ((InternalRelationType)key).getKeyIndexes()) {
      if (!indexAppliesTo(index,relation))       continue;
      IndexUpdate update;
      if (index instanceof CompositeIndexType) {
        final CompositeIndexType iIndex=(CompositeIndexType)index;
        final RecordEntry[] record=indexMatch(relation,iIndex);
        if (record == null)         continue;
        update=new IndexUpdate<>(iIndex,updateType,getIndexKey(iIndex,record),getIndexEntry(iIndex,record,relation),relation);
      }
 else {
        assert relation.valueOrNull(key) != null;
        if (((MixedIndexType)index).getField(key).getStatus() == SchemaStatus.DISABLED)         continue;
        update=getMixedIndexUpdate(relation,key,relation.valueOrNull(key),(MixedIndexType)index,updateType);
      }
      if (ttl > 0)       update.setTTL(ttl);
      updates.add(update);
    }
  }
  return updates;
}
