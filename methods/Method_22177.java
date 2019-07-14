private ServletHolder getServletHolder(final String packages){
  ServletHolder result=new ServletHolder(ServletContainer.class);
  result.setInitParameter(PackagesResourceConfig.PROPERTY_PACKAGES,Joiner.on(",").join(RestfulServer.class.getPackage().getName(),packages));
  result.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",PackagesResourceConfig.class.getName());
  result.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature",Boolean.TRUE.toString());
  result.setInitParameter("resteasy.scan.providers",Boolean.TRUE.toString());
  result.setInitParameter("resteasy.use.builtin.providers",Boolean.FALSE.toString());
  return result;
}
