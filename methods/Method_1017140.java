public static AnalyticsDumpFetchSeries setup(final CoreComponent core){
  return DaggerAnalyticsDumpFetchSeries_C.builder().coreComponent(core).build().task();
}
