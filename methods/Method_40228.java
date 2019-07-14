public static void copyJarResourcesRecursively(File destination,JarURLConnection jarConnection){
  JarFile jarFile;
  try {
    jarFile=jarConnection.getJarFile();
  }
 catch (  Exception e) {
    _.die("Failed to get jar file)");
    return;
  }
  Enumeration<JarEntry> em=jarFile.entries();
  while (em.hasMoreElements()) {
    JarEntry entry=em.nextElement();
    if (entry.getName().startsWith(jarConnection.getEntryName())) {
      String fileName=StringUtils.removeStart(entry.getName(),jarConnection.getEntryName());
      if (!fileName.equals("/")) {
        InputStream entryInputStream=null;
        try {
          entryInputStream=jarFile.getInputStream(entry);
          FileUtils.copyInputStreamToFile(entryInputStream,new File(destination,fileName));
        }
 catch (        Exception e) {
          die("Failed to copy resource: " + fileName);
        }
 finally {
          if (entryInputStream != null) {
            try {
              entryInputStream.close();
            }
 catch (            Exception e) {
            }
          }
        }
      }
    }
  }
}
