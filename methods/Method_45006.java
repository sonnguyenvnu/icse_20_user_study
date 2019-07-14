public List<String> getAllEntriesFromJar(){
  List<String> mass=new ArrayList<>();
  Enumeration<JarEntry> entries=jfile.entries();
  while (entries.hasMoreElements()) {
    JarEntry e=entries.nextElement();
    if (!e.isDirectory()) {
      mass.add(e.getName());
    }
  }
  return mass;
}
