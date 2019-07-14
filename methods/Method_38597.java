/** 
 * Configures servlet context.
 */
private void configureServletContext(final ServletContext servletContext){
  servletContext.addListener(jodd.servlet.RequestContextListener.class);
  if (decoraEnabled) {
    final FilterRegistration filter=servletContext.addFilter("decora",jodd.decora.DecoraServletFilter.class);
    filter.addMappingForUrlPatterns(null,true,contextPath);
  }
  final FilterRegistration filter=servletContext.addFilter("madvoc",jodd.madvoc.MadvocServletFilter.class);
  filter.addMappingForUrlPatterns(madvocDispatcherTypes,true,contextPath);
}
