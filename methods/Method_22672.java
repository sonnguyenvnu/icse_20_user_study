static public File getJavaHome(){
  if (Platform.isMacOS()) {
    File[] plugins=getContentFile("../PlugIns").listFiles(new FilenameFilter(){
      public boolean accept(      File dir,      String name){
        return dir.isDirectory() && name.endsWith(".jdk") && !name.startsWith(".");
      }
    }
);
    return new File(plugins[0],"Contents/Home/jre");
  }
  return getContentFile("java");
}
