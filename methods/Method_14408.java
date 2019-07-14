static public InputStream tryOpenAsArchive(File file,String mimeType,String contentType){
  String fileName=file.getName();
  try {
    if (fileName.endsWith(".tar.gz") || fileName.endsWith(".tgz")) {
      return new TarInputStream(new GZIPInputStream(new FileInputStream(file)));
    }
 else     if (fileName.endsWith(".tar.bz2")) {
      return new TarInputStream(new CBZip2InputStream(new FileInputStream(file)));
    }
 else     if (fileName.endsWith(".tar") || "application/x-tar".equals(contentType)) {
      return new TarInputStream(new FileInputStream(file));
    }
 else     if (fileName.endsWith(".zip") || "application/x-zip-compressed".equals(contentType) || "application/zip".equals(contentType) || "application/x-compressed".equals(contentType) || "multipar/x-zip".equals(contentType)) {
      return new ZipInputStream(new FileInputStream(file));
    }
 else     if (fileName.endsWith(".kmz")) {
      return new ZipInputStream(new FileInputStream(file));
    }
  }
 catch (  IOException e) {
  }
  return null;
}
