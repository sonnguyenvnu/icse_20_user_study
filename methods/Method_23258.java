/** 
 * Return a full path to an item in the data folder as a File object. See the dataPath() method for more information.
 */
public File dataFile(String where){
  File why=new File(where);
  if (why.isAbsolute())   return why;
  URL jarURL=getClass().getProtectionDomain().getCodeSource().getLocation();
  String jarPath;
  try {
    jarPath=jarURL.toURI().getPath();
  }
 catch (  URISyntaxException e) {
    e.printStackTrace();
    return null;
  }
  if (jarPath.contains("Contents/Java/")) {
    File containingFolder=new File(jarPath).getParentFile();
    File dataFolder=new File(containingFolder,"data");
    return new File(dataFolder,where);
  }
  File workingDirItem=new File(sketchPath + File.separator + "data" + File.separator + where);
  return workingDirItem;
}
