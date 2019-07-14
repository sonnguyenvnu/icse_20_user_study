/** 
 * ??content??????????????????server??????? ????????????
 * @param host     ???????server?
 * @param fileName ?????
 * @param content  ???????
 * @return ???????server??????????null??????
 */
@Override public String createRemoteFile(final String host,String fileName,List<String> content){
  checkArgument(!Strings.isNullOrEmpty(host),"invalid host.");
  checkArgument(!Strings.isNullOrEmpty(fileName),"invalid fileName.");
  checkArgument(content != null && content.size() > 0,"content is empty.");
  String localAbsolutePath=MachineProtocol.TMP_DIR + fileName;
  File tmpDir=new File(MachineProtocol.TMP_DIR);
  if (!tmpDir.exists()) {
    if (!tmpDir.mkdirs()) {
      logger.error("cannot create /tmp/cachecloud directory.");
      return null;
    }
  }
  Path path=Paths.get(MachineProtocol.TMP_DIR + fileName);
  String remotePath=MachineProtocol.CONF_DIR + fileName;
  try {
    BufferedWriter bufferedWriter=Files.newBufferedWriter(path,Charset.forName(MachineProtocol.ENCODING_UTF8));
    try {
      for (      String line : content) {
        bufferedWriter.write(line);
        bufferedWriter.newLine();
      }
    }
  finally {
      if (bufferedWriter != null)       bufferedWriter.close();
    }
  }
 catch (  IOException e) {
    logger.error("write redis config file error, ip: {}, filename: {}, content: {}, e",host,fileName,content,e);
    return null;
  }
 finally {
  }
  try {
    SSHUtil.scpFileToRemote(host,localAbsolutePath,MachineProtocol.CONF_DIR);
  }
 catch (  SSHException e) {
    logger.error("scp config file to remote server error: ip: {}, fileName: {}",host,fileName,e);
    return null;
  }
  File file=new File(localAbsolutePath);
  if (file.exists()) {
    file.delete();
  }
  return remotePath;
}
