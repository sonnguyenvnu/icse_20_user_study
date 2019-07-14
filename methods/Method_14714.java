static private boolean isWebapp(File dir){
  if (dir == null) {
    return false;
  }
  if (!dir.exists() || !dir.canRead()) {
    return false;
  }
  File webXml=new File(dir,"WEB-INF/web.xml");
  return webXml.exists() && webXml.canRead();
}
