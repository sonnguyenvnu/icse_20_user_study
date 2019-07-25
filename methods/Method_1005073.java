/** 
 * places a graph into storage, protected by the given access. <p> GraphId can't already exist, otherwise  {@link OverwritingException} is thrown.<p> Access can't be null otherwise  {@link IllegalArgumentException} isthrown
 * @param graph  the graph to add to the storage.
 * @param access access required to for the graph.
 * @throws StorageException if unable to put arguments into storage
 */
public void put(final GraphSerialisable graph,final FederatedAccess access) throws StorageException {
  if (graph != null) {
    String graphId=graph.getDeserialisedConfig().getGraphId();
    try {
      if (null == access) {
        throw new IllegalArgumentException(ACCESS_IS_NULL);
      }
      if (null != graphLibrary) {
        graphLibrary.checkExisting(graphId,graph.getDeserialisedSchema(),graph.getDeserialisedProperties());
      }
      validateExisting(graph);
      final Graph builtGraph=graph.getGraph();
      if (isCacheEnabled()) {
        addToCache(builtGraph,access);
      }
      Set<Graph> existingGraphs=storage.get(access);
      if (null == existingGraphs) {
        existingGraphs=Sets.newHashSet(builtGraph);
        storage.put(access,existingGraphs);
      }
 else {
        existingGraphs.add(builtGraph);
      }
    }
 catch (    final Exception e) {
      throw new StorageException("Error adding graph " + graphId + " to storage due to: " + e.getMessage(),e);
    }
  }
 else {
    throw new StorageException("Graph cannot be null");
  }
}
