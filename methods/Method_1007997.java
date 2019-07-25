/** 
 * Downloads the plugin and returns the file it was downloaded to. 
 */
private Path download(Terminal terminal,String pluginId,Path tmpDir) throws Exception {
  if (OFFICIAL_PLUGINS.contains(pluginId)) {
    final String url=getElasticUrl(terminal,getStagingHash(),Version.CURRENT,pluginId,Platforms.PLATFORM_NAME);
    terminal.println("-> Downloading " + pluginId + " from elastic");
    return downloadZipAndChecksum(terminal,url,tmpDir,false);
  }
  String[] coordinates=pluginId.split(":");
  if (coordinates.length == 3 && pluginId.contains("/") == false && pluginId.startsWith("file:") == false) {
    String mavenUrl=getMavenUrl(terminal,coordinates,Platforms.PLATFORM_NAME);
    terminal.println("-> Downloading " + pluginId + " from maven central");
    return downloadZipAndChecksum(terminal,mavenUrl,tmpDir,true);
  }
  if (pluginId.contains(":") == false) {
    List<String> plugins=checkMisspelledPlugin(pluginId);
    String msg="Unknown plugin " + pluginId;
    if (plugins.isEmpty() == false) {
      msg+=", did you mean " + (plugins.size() == 1 ? "[" + plugins.get(0) + "]" : "any of " + plugins.toString()) + "?";
    }
    throw new UserException(ExitCodes.USAGE,msg);
  }
  terminal.println("-> Downloading " + URLDecoder.decode(pluginId,"UTF-8"));
  return downloadZip(terminal,pluginId,tmpDir);
}
