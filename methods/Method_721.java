@Override protected void prepareResponse(HttpServletRequest request,HttpServletResponse response){
  setResponseContentType(request,response);
  response.setCharacterEncoding(fastJsonConfig.getCharset().name());
  if (this.disableCaching) {
    response.addHeader("Pragma","no-cache");
    response.addHeader("Cache-Control","no-cache, no-store, max-age=0");
    response.addDateHeader("Expires",1L);
  }
}
