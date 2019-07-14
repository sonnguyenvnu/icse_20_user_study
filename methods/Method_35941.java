@Override public void configure(HttpServletRequest request){
  MultipartConfigElement multipartConfigElement=new MultipartConfigElement((String)null);
  request.setAttribute("org.eclipse.jetty.multipartConfig",multipartConfigElement);
}
