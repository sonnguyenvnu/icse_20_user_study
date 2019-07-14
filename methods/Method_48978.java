/** 
 * Executes the query by executing its on  {@link SliceQuery} sub-query.
 * @return
 */
private Iterator<Entry> getBasicIterator(){
  final EntryList result=vertex.loadRelations(sliceQuery,query -> QueryProfiler.profile(profiler,query,q -> tx.getGraph().edgeQuery(vertex.longId(),q,tx.getTxHandle())));
  return result.iterator();
}
