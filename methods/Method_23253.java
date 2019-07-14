static protected String calcSketchPath(){
  String folder=null;
  try {
    folder=System.getProperty("user.dir");
    URL jarURL=PApplet.class.getProtectionDomain().getCodeSource().getLocation();
    String jarPath=jarURL.toURI().getSchemeSpecificPart();
    if (platform == MACOSX) {
      if (jarPath.contains("Contents/Java/")) {
        String appPath=jarPath.substring(0,jarPath.indexOf(".app") + 4);
        File containingFolder=new File(appPath).getParentFile();
        folder=containingFolder.getAbsolutePath();
      }
    }
 else {
      if (jarPath.contains("/lib/")) {
        folder=new File(jarPath,"../..").getCanonicalPath();
      }
    }
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return folder;
}
