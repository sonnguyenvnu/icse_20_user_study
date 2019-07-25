protected void process(HttpServletRequest request,HttpServletResponse response) throws IOException {
  String resource=request.getPathInfo().replace(request.getServletPath(),"");
  if (resource.startsWith("/"))   resource=resource.replaceFirst("/","");
  InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
  if (in == null) {
    throw new Error("Cannot find resource " + resource);
  }
  try {
    ByteStreams.copy(in,response.getOutputStream());
  }
  finally {
    in.close();
    Calendar c=Calendar.getInstance();
    c.setTime(new Date());
    c.add(Calendar.DATE,10);
    response.setDateHeader("Expires",c.getTime().getTime());
    response.setHeader("Cache-Control","max-age=864000");
    response.flushBuffer();
  }
}
