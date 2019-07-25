public InputStream open() throws IOException {
  final ZipInputStream zis=new ZipInputStream(new FileInputStream(zipFile));
  ZipEntry ze=zis.getNextEntry();
  while (ze != null) {
    final String fileName=ze.getName();
    if (ze.isDirectory()) {
    }
 else     if (fileName.trim().equalsIgnoreCase(entry.trim())) {
      return zis;
    }
    ze=zis.getNextEntry();
  }
  zis.closeEntry();
  zis.close();
  throw new IOException();
}
