@Override public void preFetch(){
  profiler.setAnnotation(QueryProfiler.MULTIPREFETCH_ANNOTATION,true);
  properties();
}
