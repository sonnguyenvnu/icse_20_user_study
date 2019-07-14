@Override public boolean hasNext(){
  return MetricInstrumentedStore.runWithMetrics(p,M_HAS_NEXT,(UncheckedCallable<Boolean>)iterator::hasNext);
}
