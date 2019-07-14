public static WireMockHttpServletMultipartAdapter from(Part servletPart){
  return new WireMockHttpServletMultipartAdapter(servletPart);
}
