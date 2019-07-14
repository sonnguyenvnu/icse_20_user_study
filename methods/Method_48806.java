private Graph writeMutatedPropertiesBackIntoGraph(){
  Graph resultgraph=graph;
  if (persistMode == Persist.NOTHING && resultGraphMode == ResultGraph.NEW) {
    resultgraph=EmptyGraph.instance();
  }
 else   if (persistMode != Persist.NOTHING && vertexProgram != null && !vertexProgram.getVertexComputeKeys().isEmpty()) {
    JanusGraphManagement management=graph.openManagement();
    try {
      for (      VertexComputeKey key : vertexProgram.getVertexComputeKeys()) {
        if (!management.containsPropertyKey(key.getKey())) {
          log.warn("Property key [{}] is not part of the schema and will be created. It is advised to initialize all keys.",key.getKey());
        }
        management.getOrCreatePropertyKey(key.getKey());
      }
      management.commit();
    }
  finally {
      if (management != null && management.isOpen()) {
        management.rollback();
      }
    }
    Map<Long,Map<String,Object>> mutatedProperties=Maps.transformValues(vertexMemory.getMutableVertexProperties(),new Function<Map<String,Object>,Map<String,Object>>(){
      @Nullable @Override public Map<String,Object> apply(      final Map<String,Object> o){
        return Maps.filterKeys(o,s -> !VertexProgramHelper.isTransientVertexComputeKey(s,vertexProgram.getVertexComputeKeys()));
      }
    }
);
    if (resultGraphMode == ResultGraph.ORIGINAL) {
      AtomicInteger failures=new AtomicInteger(0);
      try (WorkerPool workers=new WorkerPool(numThreads)){
        List<Map.Entry<Long,Map<String,Object>>> subset=new ArrayList<>(writeBatchSize / vertexProgram.getVertexComputeKeys().size());
        int currentSize=0;
        for (        Map.Entry<Long,Map<String,Object>> entry : mutatedProperties.entrySet()) {
          subset.add(entry);
          currentSize+=entry.getValue().size();
          if (currentSize >= writeBatchSize) {
            workers.submit(new VertexPropertyWriter(subset,failures));
            subset=new ArrayList<>(subset.size());
            currentSize=0;
          }
        }
        if (!subset.isEmpty()) {
          workers.submit(new VertexPropertyWriter(subset,failures));
        }
      }
 catch (      Exception e) {
        throw new JanusGraphException("Exception while attempting to persist result into graph",e);
      }
      if (failures.get() > 0) {
        throw new JanusGraphException("Could not persist program results to graph. Check log for details.");
      }
    }
 else     if (resultGraphMode == ResultGraph.NEW) {
      resultgraph=graph.newTransaction();
      for (      Map.Entry<Long,Map<String,Object>> vertexProperty : mutatedProperties.entrySet()) {
        Vertex v=resultgraph.vertices(vertexProperty.getKey()).next();
        for (        Map.Entry<String,Object> prop : vertexProperty.getValue().entrySet()) {
          if (prop.getValue() instanceof List) {
            ((List)prop.getValue()).forEach(value -> v.property(VertexProperty.Cardinality.list,prop.getKey(),value));
          }
 else {
            v.property(VertexProperty.Cardinality.single,prop.getKey(),prop.getValue());
          }
        }
      }
    }
  }
  return resultgraph;
}
