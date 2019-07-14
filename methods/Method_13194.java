protected String searchSource(Container.Entry entry,File sourceJarFile){
  if (sourceJarFile != null) {
    byte[] buffer=new byte[1024 * 2];
    try (ZipInputStream zis=new ZipInputStream(new BufferedInputStream(new FileInputStream(sourceJarFile)))){
      ZipEntry ze=zis.getNextEntry();
      String name=entry.getPath();
      name=name.substring(0,name.length() - 6) + ".java";
      while (ze != null) {
        if (ze.getName().equals(name)) {
          ByteArrayOutputStream out=new ByteArrayOutputStream();
          int read=zis.read(buffer);
          while (read > 0) {
            out.write(buffer,0,read);
            read=zis.read(buffer);
          }
          return new String(out.toByteArray(),"UTF-8");
        }
        ze=zis.getNextEntry();
      }
      zis.closeEntry();
    }
 catch (    IOException e) {
      assert ExceptionUtil.printStackTrace(e);
    }
  }
  return null;
}
