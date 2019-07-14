/** 
 * Get the path to the embedded Java executable. 
 */
static public String getJavaPath(){
  String javaPath="bin/java" + (Platform.isWindows() ? ".exe" : "");
  File javaFile=new File(getJavaHome(),javaPath);
  try {
    return javaFile.getCanonicalPath();
  }
 catch (  IOException e) {
    return javaFile.getAbsolutePath();
  }
}
