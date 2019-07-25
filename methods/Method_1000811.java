/** 
 * ?Web?????Nutz?????,??????????,????????? <p/> ????????
 */
public Scans init(final ServletContext sc){
  Stopwatch sw=Stopwatch.begin();
  String classesPath=sc.getRealPath("/WEB-INF/classes");
  if (classesPath == null)   addResourceLocation(new WebClassesResourceLocation(sc));
 else {
    ResourceLocation rc=ResourceLocation.file(new File(classesPath));
    if (rc instanceof FileSystemResourceLocation)     ((FileSystemResourceLocation)rc).priority=125;
    addResourceLocation(rc);
  }
  Set<String> jars=sc.getResourcePaths("/WEB-INF/lib/");
  if (jars != null) {
    for (    String path : jars) {
      if (!path.endsWith(".jar"))       continue;
      try {
        addResourceLocation(new JarResourceLocation(sc.getResource(path)));
      }
 catch (      Exception e) {
        log.debug("parse jar fail >> " + e.getMessage());
      }
    }
  }
  sw.stop();
  printLocations(sw);
  return this;
}
