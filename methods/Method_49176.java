public void executeMultiQuery(final Collection<InternalVertex> vertices,final SliceQuery sq,final QueryProfiler profiler){
  LongArrayList vertexIds=new LongArrayList(vertices.size());
  for (  InternalVertex v : vertices) {
    if (!v.isNew() && v.hasId() && (v instanceof CacheVertex) && !v.hasLoadedRelations(sq))     vertexIds.add(v.longId());
  }
  if (!vertexIds.isEmpty()) {
    List<EntryList> results=QueryProfiler.profile(profiler,sq,true,q -> graph.edgeMultiQuery(vertexIds,q,txHandle));
    int pos=0;
    for (    JanusGraphVertex v : vertices) {
      if (pos < vertexIds.size() && vertexIds.get(pos) == v.longId()) {
        final EntryList vresults=results.get(pos);
        ((CacheVertex)v).loadRelations(sq,query -> vresults);
        pos++;
      }
    }
  }
}
