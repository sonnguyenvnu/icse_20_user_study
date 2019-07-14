static private List<String> buildJavaRuntimeClassPath(){
  StringBuilder classPath=new StringBuilder();
{
    String rtPath=System.getProperty("java.home") + File.separator + "lib" + File.separator + "rt.jar";
    if (new File(rtPath).exists()) {
      classPath.append(File.pathSeparator).append(rtPath);
    }
 else {
      rtPath=System.getProperty("java.home") + File.separator + "jre" + File.separator + "lib" + File.separator + "rt.jar";
      if (new File(rtPath).exists()) {
        classPath.append(File.pathSeparator).append(rtPath);
      }
    }
  }
{
    String jfxrtPath=System.getProperty("java.home") + File.separator + "lib" + File.separator + "ext" + File.separator + "jfxrt.jar";
    if (new File(jfxrtPath).exists()) {
      classPath.append(File.pathSeparator).append(jfxrtPath);
    }
 else {
      jfxrtPath=System.getProperty("java.home") + File.separator + "jre" + File.separator + "lib" + File.separator + "ext" + File.separator + "jfxrt.jar";
      if (new File(jfxrtPath).exists()) {
        classPath.append(File.pathSeparator).append(jfxrtPath);
      }
    }
  }
  return sanitizeClassPath(classPath.toString());
}
