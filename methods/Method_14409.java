static public InputStream tryOpenAsCompressedFile(File file,String mimeType,String contentEncoding){
  String fileName=file.getName();
  try {
    if (fileName.endsWith(".gz") || "gzip".equals(contentEncoding) || "x-gzip".equals(contentEncoding) || "application/x-gzip".equals(mimeType)) {
      return new GZIPInputStream(new FileInputStream(file));
    }
 else     if (fileName.endsWith(".bz2") || "application/x-bzip2".equals(mimeType)) {
      InputStream is=new FileInputStream(file);
      is.mark(4);
      if (!(is.read() == 'B' && is.read() == 'Z')) {
        is.reset();
      }
      return new CBZip2InputStream(is);
    }
  }
 catch (  IOException e) {
    logger.warn("Something that looked like a compressed file gave an error on open: " + file,e);
  }
  return null;
}
