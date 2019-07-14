@Override public void setServletContext(ServletContext servletContext){
  LOGGER.info("===== ????zheng-admin =====");
  String version=PropertiesFileUtil.getInstance("zheng-admin-client").get("zheng.admin.version");
  LOGGER.info("zheng-admin.jar ??: {}",version);
  String jarPath=servletContext.getRealPath("/WEB-INF/lib/zheng-admin-" + version + ".jar");
  LOGGER.info("zheng-admin.jar ???: {}",jarPath);
  String resources=servletContext.getRealPath("/") + "/resources/zheng-admin";
  LOGGER.info("zheng-admin.jar ???: {}",resources);
  JarUtil.decompress(jarPath,resources);
  LOGGER.info("===== ??zheng-admin?? =====");
}
