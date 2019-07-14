protected static void registerPluginForMimeType(String[] indexFiles,String mimeType,WebServerPlugin plugin,Map<String,String> commandLineOptions){
  if (mimeType == null || plugin == null) {
    return;
  }
  if (indexFiles != null) {
    for (    String filename : indexFiles) {
      int dot=filename.lastIndexOf('.');
      if (dot >= 0) {
        String extension=filename.substring(dot + 1).toLowerCase();
        mimeTypes().put(extension,mimeType);
      }
    }
    SimpleWebServer.INDEX_FILE_NAMES.addAll(Arrays.asList(indexFiles));
  }
  SimpleWebServer.mimeTypeHandlers.put(mimeType,plugin);
  plugin.initialize(commandLineOptions);
}
