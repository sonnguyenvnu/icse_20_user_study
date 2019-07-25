private void unzip(String zipFileName,String targetDir){
  InputStream inFile=null;
  try {
    inFile=FileUtils.newInputStream(zipFileName);
    ZipInputStream zipIn=new ZipInputStream(inFile);
    while (true) {
      ZipEntry entry=zipIn.getNextEntry();
      if (entry == null) {
        break;
      }
      String fileName=entry.getName();
      fileName=fileName.replace('\\',SysProperties.FILE_SEPARATOR.charAt(0));
      fileName=fileName.replace('/',SysProperties.FILE_SEPARATOR.charAt(0));
      if (fileName.startsWith(SysProperties.FILE_SEPARATOR)) {
        fileName=fileName.substring(1);
      }
      OutputStream o=null;
      try {
        o=FileUtils.newOutputStream(targetDir + SysProperties.FILE_SEPARATOR + fileName,false);
        IOUtils.copy(zipIn,o);
        o.close();
      }
  finally {
        IOUtils.closeSilently(o);
      }
      zipIn.closeEntry();
    }
    zipIn.closeEntry();
    zipIn.close();
  }
 catch (  IOException e) {
    error(e);
  }
 finally {
    IOUtils.closeSilently(inFile);
  }
}
