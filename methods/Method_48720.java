public ModificationSummary prepareCommit(final Collection<InternalRelation> addedRelations,final Collection<InternalRelation> deletedRelations,final Predicate<InternalRelation> filter,final BackendTransaction mutator,final StandardJanusGraphTx tx,final boolean acquireLocks) throws BackendException {
  ListMultimap<Long,InternalRelation> mutations=ArrayListMultimap.create();
  ListMultimap<InternalVertex,InternalRelation> mutatedProperties=ArrayListMultimap.create();
  List<IndexSerializer.IndexUpdate> indexUpdates=Lists.newArrayList();
  for (  InternalRelation del : Iterables.filter(deletedRelations,filter)) {
    Preconditions.checkArgument(del.isRemoved());
    for (int pos=0; pos < del.getLen(); pos++) {
      InternalVertex vertex=del.getVertex(pos);
      if (pos == 0 || !del.isLoop()) {
        if (del.isProperty())         mutatedProperties.put(vertex,del);
        mutations.put(vertex.longId(),del);
      }
      if (acquireLock(del,pos,acquireLocks)) {
        Entry entry=edgeSerializer.writeRelation(del,pos,tx);
        mutator.acquireEdgeLock(idManager.getKey(vertex.longId()),entry);
      }
    }
    indexUpdates.addAll(indexSerializer.getIndexUpdates(del));
  }
  for (  InternalRelation add : Iterables.filter(addedRelations,filter)) {
    Preconditions.checkArgument(add.isNew());
    for (int pos=0; pos < add.getLen(); pos++) {
      InternalVertex vertex=add.getVertex(pos);
      if (pos == 0 || !add.isLoop()) {
        if (add.isProperty())         mutatedProperties.put(vertex,add);
        mutations.put(vertex.longId(),add);
      }
      if (!vertex.isNew() && acquireLock(add,pos,acquireLocks)) {
        Entry entry=edgeSerializer.writeRelation(add,pos,tx);
        mutator.acquireEdgeLock(idManager.getKey(vertex.longId()),entry.getColumn());
      }
    }
    indexUpdates.addAll(indexSerializer.getIndexUpdates(add));
  }
  for (  InternalVertex v : mutatedProperties.keySet()) {
    indexUpdates.addAll(indexSerializer.getIndexUpdates(v,mutatedProperties.get(v)));
  }
  for (  IndexSerializer.IndexUpdate update : indexUpdates) {
    if (!update.isCompositeIndex() || !update.isDeletion())     continue;
    CompositeIndexType iIndex=(CompositeIndexType)update.getIndex();
    if (acquireLock(iIndex,acquireLocks)) {
      mutator.acquireIndexLock((StaticBuffer)update.getKey(),(Entry)update.getEntry());
    }
  }
  for (  IndexSerializer.IndexUpdate update : indexUpdates) {
    if (!update.isCompositeIndex() || !update.isAddition())     continue;
    CompositeIndexType iIndex=(CompositeIndexType)update.getIndex();
    if (acquireLock(iIndex,acquireLocks)) {
      mutator.acquireIndexLock((StaticBuffer)update.getKey(),((Entry)update.getEntry()).getColumn());
    }
  }
  for (  Long vertexId : mutations.keySet()) {
    Preconditions.checkArgument(vertexId > 0,"Vertex has no id: %s",vertexId);
    final List<InternalRelation> edges=mutations.get(vertexId);
    final List<Entry> additions=new ArrayList<>(edges.size());
    final List<Entry> deletions=new ArrayList<>(Math.max(10,edges.size() / 10));
    for (    final InternalRelation edge : edges) {
      final InternalRelationType baseType=(InternalRelationType)edge.getType();
      assert baseType.getBaseType() == null;
      for (      InternalRelationType type : baseType.getRelationIndexes()) {
        if (type.getStatus() == SchemaStatus.DISABLED)         continue;
        for (int pos=0; pos < edge.getArity(); pos++) {
          if (!type.isUnidirected(Direction.BOTH) && !type.isUnidirected(EdgeDirection.fromPosition(pos)))           continue;
          if (edge.getVertex(pos).longId() == vertexId) {
            StaticArrayEntry entry=edgeSerializer.writeRelation(edge,type,pos,tx);
            if (edge.isRemoved()) {
              deletions.add(entry);
            }
 else {
              Preconditions.checkArgument(edge.isNew());
              int ttl=getTTL(edge);
              if (ttl > 0) {
                entry.setMetaData(EntryMetaData.TTL,ttl);
              }
              additions.add(entry);
            }
          }
        }
      }
    }
    StaticBuffer vertexKey=idManager.getKey(vertexId);
    mutator.mutateEdges(vertexKey,additions,deletions);
  }
  boolean has2iMods=false;
  for (  IndexSerializer.IndexUpdate indexUpdate : indexUpdates) {
    assert indexUpdate.isAddition() || indexUpdate.isDeletion();
    if (indexUpdate.isCompositeIndex()) {
      final IndexSerializer.IndexUpdate<StaticBuffer,Entry> update=indexUpdate;
      if (update.isAddition())       mutator.mutateIndex(update.getKey(),Lists.newArrayList(update.getEntry()),KCVSCache.NO_DELETIONS);
 else       mutator.mutateIndex(update.getKey(),KeyColumnValueStore.NO_ADDITIONS,Lists.newArrayList(update.getEntry()));
    }
 else {
      final IndexSerializer.IndexUpdate<String,IndexEntry> update=indexUpdate;
      has2iMods=true;
      IndexTransaction itx=mutator.getIndexTransaction(update.getIndex().getBackingIndexName());
      String indexStore=((MixedIndexType)update.getIndex()).getStoreName();
      if (update.isAddition())       itx.add(indexStore,update.getKey(),update.getEntry(),update.getElement().isNew());
 else       itx.delete(indexStore,update.getKey(),update.getEntry().field,update.getEntry().value,update.getElement().isRemoved());
    }
  }
  return new ModificationSummary(!mutations.isEmpty(),has2iMods);
}
