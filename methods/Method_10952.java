/** 
 * ????????zip???????? <p> ?????????,??????,??????????????
 * @param zipFile zip???????
 * @param destDir ?????????
 * @param passwd  ??(???)
 * @return ????????
 * @throws ZipException
 */
@SuppressWarnings("unchecked") public static List<File> unzipFileByKeyword(File zipFile,File destDir,String passwd){
  try {
    if (zipFile == null) {
      throw new ZipException("???????.");
    }
    if (destDir == null) {
      throw new ZipException("????????.");
    }
    if (destDir.isDirectory() && !destDir.exists()) {
      destDir.mkdir();
    }
    net.lingala.zip4j.core.ZipFile zFile=new net.lingala.zip4j.core.ZipFile(zipFile);
    zFile.setFileNameCharset("UTF-8");
    if (!zFile.isValidZipFile()) {
      throw new ZipException("???????,?????.");
    }
    if (zFile.isEncrypted()) {
      zFile.setPassword(passwd.toCharArray());
    }
    zFile.extractAll(destDir.getAbsolutePath());
    List<FileHeader> headerList=zFile.getFileHeaders();
    List<File> extractedFileList=new ArrayList<File>();
    for (    FileHeader fileHeader : headerList) {
      if (!fileHeader.isDirectory()) {
        extractedFileList.add(new File(destDir,fileHeader.getFileName()));
      }
    }
    return extractedFileList;
  }
 catch (  net.lingala.zip4j.exception.ZipException e) {
    e.printStackTrace();
    return null;
  }
}
