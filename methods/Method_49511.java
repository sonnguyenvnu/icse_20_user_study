private static Conf configureByClasspath(String mapReduceJarFilename){
  final List<Path> paths=new LinkedList<>();
  final String classpath=System.getProperty("java.class.path");
  final String mrj=mapReduceJarFilename.toLowerCase();
  String mapReduceJarPath=null;
  for (  String classPathEntry : classpath.split(File.pathSeparator)) {
    if (classPathEntry.toLowerCase().endsWith(".jar") || classPathEntry.toLowerCase().endsWith(".properties")) {
      paths.add(new Path(classPathEntry));
      if (classPathEntry.toLowerCase().endsWith(mrj)) {
        mapReduceJarPath=classPathEntry;
      }
    }
  }
  return new Conf(paths,mapReduceJarPath);
}
