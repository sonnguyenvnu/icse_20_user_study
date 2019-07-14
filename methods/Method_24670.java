protected void addClasses(ZipOutputStream zos,File dir) throws IOException {
  String path=dir.getAbsolutePath();
  if (!path.endsWith("/") && !path.endsWith("\\")) {
    path+='/';
  }
  addClasses(zos,dir,path);
}
