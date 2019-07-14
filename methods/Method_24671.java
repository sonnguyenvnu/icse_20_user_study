protected void addClasses(ZipOutputStream zos,File dir,String rootPath) throws IOException {
  File files[]=dir.listFiles(new FilenameFilter(){
    public boolean accept(    File dir,    String name){
      return (name.charAt(0) != '.');
    }
  }
);
  for (  File sub : files) {
    String relativePath=sub.getAbsolutePath().substring(rootPath.length());
    if (sub.isDirectory()) {
      addClasses(zos,sub,rootPath);
    }
 else     if (sub.getName().endsWith(".class")) {
      ZipEntry entry=new ZipEntry(relativePath);
      zos.putNextEntry(entry);
      PApplet.saveStream(zos,new FileInputStream(sub));
      zos.closeEntry();
    }
  }
}
