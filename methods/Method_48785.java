private void createIndexIfDoesNotExist(String indexName,String propertyKeyName,Class dataType,boolean unique){
  graph.tx().rollback();
  JanusGraphManagement management=graph.openManagement();
  if (null == management.getGraphIndex(indexName)) {
    final PropertyKey key;
    boolean propertyKeyAlreadyExisted=false;
    if (null == management.getPropertyKey(propertyKeyName)) {
      key=management.makePropertyKey(propertyKeyName).dataType(dataType).make();
    }
 else {
      key=management.getPropertyKey(propertyKeyName);
      propertyKeyAlreadyExisted=true;
    }
    final JanusGraphIndex index;
    if (unique)     index=management.buildIndex(indexName,Vertex.class).addKey(key).unique().buildCompositeIndex();
 else     index=management.buildIndex(indexName,Vertex.class).addKey(key).buildCompositeIndex();
    try {
      if (index.getIndexStatus(key) == INSTALLED) {
        management.commit();
        ManagementSystem.awaitGraphIndexStatus(graph,indexName).call();
        management=graph.openManagement();
        if (propertyKeyAlreadyExisted) {
          management.updateIndex(management.getGraphIndex(indexName),REINDEX).get();
        }
 else {
          management.updateIndex(management.getGraphIndex(indexName),ENABLE_INDEX).get();
        }
      }
 else       if (index.getIndexStatus(key) == REGISTERED) {
        if (propertyKeyAlreadyExisted) {
          management.updateIndex(management.getGraphIndex(indexName),REINDEX).get();
        }
 else {
          management.updateIndex(management.getGraphIndex(indexName),ENABLE_INDEX).get();
        }
      }
    }
 catch (    InterruptedException|ExecutionException e) {
      log.warn("Failed to create index {} for ConfigurationManagementGraph with exception: {}",indexName,e.toString());
      management.rollback();
      graph.tx().rollback();
    }
    management.commit();
    graph.tx().commit();
  }
 else {
    management.rollback();
    graph.tx().rollback();
  }
}
