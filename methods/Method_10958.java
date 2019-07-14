public static double zipInfo(String zipFile) throws ZipException {
  net.lingala.zip4j.core.ZipFile zip=new net.lingala.zip4j.core.ZipFile(zipFile);
  zip.setFileNameCharset("GBK");
  List<FileHeader> list=zip.getFileHeaders();
  long zipCompressedSize=0;
  for (  FileHeader head : list) {
    zipCompressedSize+=head.getCompressedSize();
  }
  double size=zipCompressedSize / 1.0 / 1024;
  return size;
}
