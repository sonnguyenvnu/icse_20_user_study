/** 
 * Gets all the jar files contained in the jars folder. (For Java Migrations)
 * @param config      The configured properties.
 * @return The jar files.
 */
private static List<File> getJavaMigrationJarFiles(Map<String,String> config){
  String jarDirs=config.get(ConfigUtils.JAR_DIRS);
  if (!StringUtils.hasLength(jarDirs)) {
    return Collections.emptyList();
  }
  jarDirs=jarDirs.replace(File.pathSeparator,",");
  String[] dirs=StringUtils.tokenizeToStringArray(jarDirs,",");
  List<File> jarFiles=new ArrayList<>();
  for (  String dirName : dirs) {
    File dir=new File(dirName);
    File[] files=dir.listFiles(new FilenameFilter(){
      public boolean accept(      File dir,      String name){
        return name.endsWith(".jar");
      }
    }
);
    if (files == null) {
      LOG.error("Directory for Java Migrations not found: " + dirName);
      System.exit(1);
    }
    jarFiles.addAll(Arrays.asList(files));
  }
  return jarFiles;
}
