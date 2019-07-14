/** 
 * @param base name of the class, with or without the package
 * @param file
 * @return name of class (with full package name) or null if not found
 */
static protected String findClassInZipFile(String base,File file){
  String classFileName="/" + base + ".class";
  try {
    ZipFile zipFile=new ZipFile(file);
    Enumeration<?> entries=zipFile.entries();
    while (entries.hasMoreElements()) {
      ZipEntry entry=(ZipEntry)entries.nextElement();
      if (!entry.isDirectory()) {
        String name=entry.getName();
        if (name.endsWith(classFileName)) {
          zipFile.close();
          return name.substring(0,name.length() - 6).replace('/','.');
        }
      }
    }
    zipFile.close();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
