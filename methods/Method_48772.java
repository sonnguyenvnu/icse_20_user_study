private void restoreExternalIndexes(Predicate<String> isFailedIndex,TransactionLogHeader.Entry entry){
  SetMultimap<String,IndexRestore> indexRestores=HashMultimap.create();
  BackendOperation.execute(() -> {
    final StandardJanusGraphTx tx=(StandardJanusGraphTx)graph.newTransaction();
    try {
      entry.getContentAsModifications(serializer).stream().map(m -> ModificationDeserializer.parseRelation(m,tx)).forEach(rel -> {
        for (        final MixedIndexType index : getMixedIndexes(rel.getType())) {
          if (index.getElement() == ElementCategory.VERTEX && isFailedIndex.apply(index.getBackingIndexName())) {
            assert rel.isProperty();
            indexRestores.put(index.getBackingIndexName(),new IndexRestore(rel.getVertex(0).longId(),ElementCategory.VERTEX,getIndexId(index)));
          }
        }
        for (        final RelationType relType : rel.getPropertyKeysDirect()) {
          for (          final MixedIndexType index : getMixedIndexes(relType)) {
            if (index.getElement().isInstance(rel) && isFailedIndex.apply(index.getBackingIndexName())) {
              assert rel.id() instanceof RelationIdentifier;
              indexRestores.put(index.getBackingIndexName(),new IndexRestore(rel.id(),ElementCategory.getByClazz(rel.getClass()),getIndexId(index)));
            }
          }
        }
      }
);
    }
  finally {
      if (tx.isOpen())       tx.rollback();
    }
    return true;
  }
,readTime);
  for (  final String indexName : indexRestores.keySet()) {
    final StandardJanusGraphTx tx=(StandardJanusGraphTx)graph.newTransaction();
    try {
      BackendTransaction btx=tx.getTxHandle();
      final IndexTransaction indexTx=btx.getIndexTransaction(indexName);
      BackendOperation.execute(new Callable<Boolean>(){
        @Override public Boolean call() throws Exception {
          Map<String,Map<String,List<IndexEntry>>> restoredDocs=Maps.newHashMap();
          indexRestores.get(indexName).forEach(restore -> {
            JanusGraphSchemaVertex indexV=(JanusGraphSchemaVertex)tx.getVertex(restore.indexId);
            MixedIndexType index=(MixedIndexType)indexV.asIndexType();
            JanusGraphElement element=restore.retrieve(tx);
            if (element != null) {
              graph.getIndexSerializer().reindexElement(element,index,restoredDocs);
            }
 else {
              graph.getIndexSerializer().removeElement(restore.elementId,index,restoredDocs);
            }
          }
);
          indexTx.restore(restoredDocs);
          indexTx.commit();
          return true;
        }
        @Override public String toString(){
          return "IndexMutation";
        }
      }
,persistenceTime);
    }
  finally {
      if (tx.isOpen())       tx.rollback();
    }
  }
}
