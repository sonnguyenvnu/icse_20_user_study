/** 
 * This initialisation method is called the first time this instance is used and also when an attempt to retrieve a vertex from the cached multiQuery results doesn't find an entry.
 * @param vertices A list of vertices with which to initialise the multiQuery
 */
private void initializeMultiQuery(final List<Traverser.Admin<Element>> list){
  assert list.size() > 0;
  final JanusGraphMultiVertexQuery multiQuery=JanusGraphTraversalUtil.getTx(traversal).multiQuery();
  list.forEach(v -> multiQuery.addVertex((Vertex)v.get()));
  makeQuery(multiQuery);
  Map<JanusGraphVertex,Iterable<? extends JanusGraphProperty>> results=multiQuery.properties();
  if (multiQueryResults == null) {
    multiQueryResults=results;
  }
 else {
    multiQueryResults.putAll(results);
  }
  initialized=true;
}
