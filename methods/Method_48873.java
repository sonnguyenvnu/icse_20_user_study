@Override public void observeWith(QueryProfiler parentProfiler){
  Preconditions.checkArgument(parentProfiler != null);
  this.profiler=parentProfiler.addNested(QueryProfiler.OR_QUERY);
  profiler.setAnnotation(QueryProfiler.FITTED_ANNOTATION,isFitted);
  profiler.setAnnotation(QueryProfiler.ORDERED_ANNOTATION,isSorted);
  profiler.setAnnotation(QueryProfiler.QUERY_ANNOTATION,backendQuery);
  if (backendQuery instanceof ProfileObservable)   ((ProfileObservable)backendQuery).observeWith(profiler);
}
