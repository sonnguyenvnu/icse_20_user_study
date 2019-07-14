/** 
 * load filters from SPI ServiceLoader
 * @see ServiceLoader
 */
private void initFromSPIServiceLoader(){
  if (loadSpifilterSkip) {
    return;
  }
  if (autoFilters == null) {
    List<Filter> filters=new ArrayList<Filter>();
    ServiceLoader<Filter> autoFilterLoader=ServiceLoader.load(Filter.class);
    for (    Filter filter : autoFilterLoader) {
      AutoLoad autoLoad=filter.getClass().getAnnotation(AutoLoad.class);
      if (autoLoad != null && autoLoad.value()) {
        filters.add(filter);
      }
    }
    autoFilters=filters;
  }
  for (  Filter filter : autoFilters) {
    if (LOG.isInfoEnabled()) {
      LOG.info("load filter from spi :" + filter.getClass().getName());
    }
    addFilter(filter);
  }
}
