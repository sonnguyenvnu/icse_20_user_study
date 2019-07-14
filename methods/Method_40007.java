private File createBizFile() throws IOException {
  String url=typeCommandOption.getArgs()[0];
  String fileName=parseFileName(url);
  URL bizUrl=new URL(url);
  File workingDir=new File(EnvironmentUtils.getProperty(Constants.JARSLINK_WORKING_DIR));
  File bizFile=new File(workingDir,fileName);
  FileUtils.copyInputStreamToFile(bizUrl.openStream(),bizFile);
  return bizFile;
}
