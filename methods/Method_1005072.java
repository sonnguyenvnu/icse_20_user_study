/** 
 * places a collections of graphs into storage, protected by the given access.
 * @param graphs the graphs to add to the storage.
 * @param access access required to for the graphs, can't be null
 * @throws StorageException if unable to put arguments into storage
 * @see #put(GraphSerialisable,FederatedAccess)
 */
public void put(final Collection<GraphSerialisable> graphs,final FederatedAccess access) throws StorageException {
  for (  final GraphSerialisable graph : graphs) {
    put(graph,access);
  }
}
