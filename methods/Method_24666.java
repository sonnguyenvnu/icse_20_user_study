/** 
 * Export to application via GUI.
 */
protected boolean exportApplication() throws IOException, SketchException {
  String foundName=build(true);
  if (foundName == null)   return false;
  if (!sketch.getName().equals(foundName)) {
    Messages.showWarning("Error during export","Sketch name is " + sketch.getName() + " but the sketch\n" + "name in the code was " + foundName,null);
    return false;
  }
  File folder=null;
  for (  String platformName : PConstants.platformNames) {
    int platform=Platform.getIndex(platformName);
    boolean embedJava=(platform == PApplet.platform) && Preferences.getBoolean("export.application.embed_java");
    if (Preferences.getBoolean(JavaEditor.EXPORT_PREFIX + platformName)) {
      final int bits=Platform.getNativeBits();
      final String arch=Platform.getNativeArch();
      if (Library.hasMultipleArch(platform,importedLibraries)) {
        if (platform != PConstants.MACOSX) {
          folder=new File(sketch.getFolder(),"application." + platformName + "32");
          if (!exportApplication(folder,platform,"32",embedJava && (bits == 32) && ("x86".equals(arch) || "i386".equals(arch)))) {
            return false;
          }
        }
        folder=new File(sketch.getFolder(),"application." + platformName + "64");
        if (!exportApplication(folder,platform,"64",embedJava && (bits == 64) && "amd64".equals(arch))) {
          return false;
        }
        if (platform == PConstants.LINUX) {
          folder=new File(sketch.getFolder(),"application.linux-armv6hf");
          if (!exportApplication(folder,platform,"armv6hf",embedJava && (bits == 32) && "arm".equals(arch))) {
            return false;
          }
          folder=new File(sketch.getFolder(),"application.linux-arm64");
          if (!exportApplication(folder,platform,"arm64",embedJava && (bits == 64) && "aarch64".equals(arch))) {
            return false;
          }
        }
      }
 else {
        folder=new File(sketch.getFolder(),"application." + platformName);
        if (!exportApplication(folder,platform,"",embedJava)) {
          return false;
        }
      }
    }
  }
  return true;
}
