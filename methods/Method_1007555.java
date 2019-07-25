public URL url(String name){
  String cname=makeResourceName(name);
  URL url=pcl.getResource(cname), ret=null;
  if (url == null)   url=ClassLoader.getSystemResource(cname);
  if (url == null)   return null;
  String path=url.getPath();
  boolean matches=path.endsWith(cname);
  assert matches : "url code source doesn't match expectation: " + name + ", " + path;
  if (!matches)   return null;
  try {
    ret=new URL(url,path.replace(cname,""));
  }
 catch (  Exception ex) {
  }
  return ret;
}
