protected void internalImportURL(HttpServletRequest request,Properties options,long projectID,String urlString) throws Exception {
  URL url=new URL(urlString);
  URLConnection connection=null;
  try {
    connection=url.openConnection();
    connection.setConnectTimeout(5000);
    connection.connect();
  }
 catch (  Exception e) {
    throw new Exception("Cannot connect to " + urlString,e);
  }
  InputStream inputStream=null;
  try {
    inputStream=connection.getInputStream();
  }
 catch (  Exception e) {
    throw new Exception("Cannot retrieve content from " + url,e);
  }
  try {
    ProjectManager.singleton.importProject(projectID,inputStream,!urlString.endsWith(".tar"));
  }
  finally {
    inputStream.close();
  }
}
