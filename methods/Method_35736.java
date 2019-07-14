private static File getRootFile(ServletContext servletContext,String rootPath){
  String containerRootPath=servletContext.getRealPath(rootPath);
  servletContext.log("rootPath: " + rootPath);
  return new File(containerRootPath);
}
