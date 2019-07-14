@Override public void configure(HttpServletRequest request){
  MultipartConfigElement multipartConfigElement=new MultipartConfigElement((String)null);
  request.setAttribute("org.eclipse.jetty.multipartConfig",multipartConfigElement);
  try {
    InputStream inputStream=request.getInputStream();
    MultiPartInputStreamParser inputStreamParser=new MultiPartInputStreamParser(inputStream,request.getContentType(),null,null);
    request.setAttribute("org.eclipse.jetty.multiPartInputStream",inputStreamParser);
  }
 catch (  IOException e) {
    Exceptions.throwUnchecked(e);
  }
}
