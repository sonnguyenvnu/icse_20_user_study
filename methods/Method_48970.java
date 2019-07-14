/** 
 * Constructs the BaseVertexCentricQuery through  {@link BasicVertexCentricQueryBuilder#constructQuery(org.janusgraph.graphdb.internal.RelationCategory)}. If the query asks for an implicit key, the resulting map is computed and returned directly. If the query is empty, a map that maps each vertex to an empty list is returned. Otherwise, the query is executed for all vertices through the transaction which will effectively pre-load the return result sets into the associated  {@link org.janusgraph.graphdb.vertices.CacheVertex} ordon't do anything at all if the vertex is new (and hence no edges in the storage backend). After that, a map is constructed that maps each vertex to the corresponding VertexCentricQuery and wrapped into a QueryProcessor. Hence, upon iteration the query will be executed like any other VertexCentricQuery with the performance difference that the SliceQueries will have already been preloaded and not further calls to the storage backend are needed.
 * @param returnType
 * @return
 */
protected <Q>Map<JanusGraphVertex,Q> execute(RelationCategory returnType,ResultConstructor<Q> resultConstructor){
  Preconditions.checkArgument(!vertices.isEmpty(),"Need to add at least one vertex to query");
  final Map<JanusGraphVertex,Q> result=new HashMap<>(vertices.size());
  BaseVertexCentricQuery bq=super.constructQuery(returnType);
  profiler.setAnnotation(QueryProfiler.MULTIQUERY_ANNOTATION,true);
  profiler.setAnnotation(QueryProfiler.NUMVERTICES_ANNOTATION,vertices.size());
  if (!bq.isEmpty()) {
    for (    BackendQueryHolder<SliceQuery> sq : bq.getQueries()) {
      Set<InternalVertex> adjVertices=Sets.newHashSet(vertices);
      for (      InternalVertex v : vertices) {
        if (isPartitionedVertex(v)) {
          profiler.setAnnotation(QueryProfiler.PARTITIONED_VERTEX_ANNOTATION,true);
          adjVertices.remove(v);
          adjVertices.addAll(allRequiredRepresentatives(v));
        }
      }
      profiler.setAnnotation(QueryProfiler.NUMVERTICES_ANNOTATION,adjVertices.size());
      tx.executeMultiQuery(adjVertices,sq.getBackendQuery(),sq.getProfiler());
    }
    for (    InternalVertex v : vertices) {
      result.put(v,resultConstructor.getResult(v,bq));
    }
  }
 else {
    for (    JanusGraphVertex v : vertices)     result.put(v,resultConstructor.emptyResult());
  }
  return result;
}
