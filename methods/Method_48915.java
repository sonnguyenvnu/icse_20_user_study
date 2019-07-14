@Override public void observeWith(QueryProfiler profiler){
  queries.forEach(q -> q.observeWith(profiler));
}
