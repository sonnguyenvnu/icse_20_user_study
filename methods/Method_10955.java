public static String zipEncrypt(String src,String dest,boolean isCreateDir,String passwd){
  File srcFile=new File(src);
  dest=buildDestinationZipFilePath(srcFile,dest);
  ZipParameters parameters=new ZipParameters();
  parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
  parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
  if (!RxDataTool.isNullString(passwd)) {
    parameters.setEncryptFiles(true);
    parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
    parameters.setPassword(passwd.toCharArray());
  }
  try {
    net.lingala.zip4j.core.ZipFile zipFile=new net.lingala.zip4j.core.ZipFile(dest);
    if (srcFile.isDirectory()) {
      if (!isCreateDir) {
        File[] subFiles=srcFile.listFiles();
        ArrayList<File> temp=new ArrayList<File>();
        Collections.addAll(temp,subFiles);
        zipFile.addFiles(temp,parameters);
        return dest;
      }
      zipFile.addFolder(srcFile,parameters);
    }
 else {
      zipFile.addFile(srcFile,parameters);
    }
    return dest;
  }
 catch (  ZipException e) {
    e.printStackTrace();
  }
  return null;
}
