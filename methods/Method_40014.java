public void initWorkingDir(){
  String workingDir=EnvironmentUtils.getProperty(Constants.JARSLINK_WORKING_DIR);
  File dirFile=StringUtils.isEmpty(workingDir) ? null : new File(workingDir);
  if (StringUtils.isEmpty(workingDir)) {
    workingDir=FileUtils.getTempDirectoryPath() + File.separator + Constants.JARSLINK_IDENTITY;
    dirFile=new File(workingDir);
    FileUtils.deleteQuietly(dirFile);
    dirFile.mkdir();
    dirFile.deleteOnExit();
    EnvironmentUtils.setProperty(Constants.JARSLINK_WORKING_DIR,workingDir);
  }
  AssertUtils.isTrue(dirFile.exists() && dirFile.isDirectory(),"Jarslink Working directory must exist.");
}
