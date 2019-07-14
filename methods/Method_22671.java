/** 
 * Get reference to a file adjacent to the executable on Windows and Linux, or inside Contents/Resources/Java on Mac OS X. This will return the local JRE location, *whether or not it is the active JRE*.
 */
static public File getContentFile(String name){
  if (processingRoot == null) {
    URL pathURL=Base.class.getProtectionDomain().getCodeSource().getLocation();
    String decodedPath;
    try {
      decodedPath=pathURL.toURI().getSchemeSpecificPart();
    }
 catch (    URISyntaxException e) {
      e.printStackTrace();
      return null;
    }
    if (decodedPath.contains("/app/bin")) {
      final File build=new File(decodedPath,"../../build").getAbsoluteFile();
      if (Platform.isMacOS()) {
        processingRoot=new File(build,"macosx/work/Processing.app/Contents/Java");
      }
 else       if (Platform.isWindows()) {
        processingRoot=new File(build,"windows/work");
      }
 else       if (Platform.isLinux()) {
        processingRoot=new File(build,"linux/work");
      }
    }
 else {
      File jarFolder=new File(decodedPath).getParentFile();
      if (jarFolder.getName().equals("lib")) {
        processingRoot=jarFolder.getParentFile();
      }
 else       if (Platform.isMacOS()) {
        processingRoot=jarFolder;
      }
      if (processingRoot == null || !processingRoot.exists()) {
        System.err.println("Could not find lib folder via " + jarFolder.getAbsolutePath() + ", switching to user.dir");
        processingRoot=new File("");
      }
    }
  }
  return new File(processingRoot,name);
}
