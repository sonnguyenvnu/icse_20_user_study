/** 
 * ??????
 * @param host
 * @param fileName
 * @param content
 */
public boolean createRemoteFile(String host,String fileName,String content){
  String localAbsolutePath=MachineProtocol.TMP_DIR + fileName;
  File tmpDir=new File(MachineProtocol.TMP_DIR);
  if (!tmpDir.exists()) {
    if (!tmpDir.mkdirs()) {
      logger.error("cannot create /tmp/cachecloud directory.");
    }
  }
  Path path=Paths.get(MachineProtocol.TMP_DIR + fileName);
  BufferedWriter bufferedWriter=null;
  try {
    bufferedWriter=Files.newBufferedWriter(path,Charset.forName(MachineProtocol.ENCODING_UTF8));
    bufferedWriter.write(content);
  }
 catch (  IOException e) {
    logger.error("write rmt file error, ip: {}, filename: {}, content: {}",host,fileName,content,e);
    return false;
  }
 finally {
    if (bufferedWriter != null) {
      try {
        bufferedWriter.close();
      }
 catch (      IOException e) {
        logger.error(e.getMessage(),e);
      }
    }
  }
  try {
    SSHUtil.scpFileToRemote(host,localAbsolutePath,ConstUtils.getRedisMigrateToolDir());
  }
 catch (  SSHException e) {
    logger.error("scp rmt file to remote server error: ip: {}, fileName: {}",host,fileName,e);
    return false;
  }
  File file=new File(localAbsolutePath);
  if (file.exists()) {
    file.delete();
  }
  return true;
}
