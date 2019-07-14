/** 
 * Writes created class content to output folder for debugging purposes.
 */
protected void dumpClassInDebugFolder(final byte[] bytes){
  File debugFolder=proxetta.getDebugFolder();
  if (debugFolder == null) {
    return;
  }
  if (!debugFolder.exists() || !debugFolder.isDirectory()) {
    log.warn("Invalid debug folder: " + debugFolder);
  }
  String fileName=proxyClassName;
  if (fileName == null) {
    fileName="proxetta-" + System.currentTimeMillis();
  }
  fileName+=".class";
  File file=new File(debugFolder,fileName);
  try {
    FileUtil.writeBytes(file,bytes);
  }
 catch (  IOException ioex) {
    log.warn("Error writing class as " + file,ioex);
  }
}
