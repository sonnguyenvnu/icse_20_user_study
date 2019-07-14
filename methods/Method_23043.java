static private void packageListFromZip(String filename,StringList list){
  try {
    ZipFile file=new ZipFile(filename);
    Enumeration<?> entries=file.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry=(ZipEntry)entries.nextElement();
      if (!entry.isDirectory()) {
        String name=entry.getName();
        if (name.endsWith(".class") && !name.startsWith("META-INF/")) {
          int slash=name.lastIndexOf('/');
          if (slash != -1) {
            String packageName=name.substring(0,slash);
            list.appendUnique(packageName);
          }
        }
      }
    }
    file.close();
  }
 catch (  IOException e) {
    System.err.println("Ignoring " + filename + " (" + e.getMessage() + ")");
  }
}
