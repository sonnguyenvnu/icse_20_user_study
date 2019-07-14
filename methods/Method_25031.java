private boolean canServeUri(String uri,File homeDir){
  boolean canServeUri;
  File f=new File(homeDir,uri);
  canServeUri=f.exists();
  if (!canServeUri) {
    WebServerPlugin plugin=SimpleWebServer.mimeTypeHandlers.get(getMimeTypeForFile(uri));
    if (plugin != null) {
      canServeUri=plugin.canServeUri(uri,homeDir);
    }
  }
  return canServeUri;
}
