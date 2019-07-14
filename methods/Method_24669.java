protected void addManifest(ZipOutputStream zos) throws IOException {
  ZipEntry entry=new ZipEntry("META-INF/MANIFEST.MF");
  zos.putNextEntry(entry);
  String contents="Manifest-Version: 1.0\n" + "Created-By: Processing " + Base.getVersionName() + "\n" + "Main-Class: " + sketch.getName() + "\n";
  zos.write(contents.getBytes());
  zos.closeEntry();
}
