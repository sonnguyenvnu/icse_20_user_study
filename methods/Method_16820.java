private static State saveTmpFile(File tmpFile,String path){
  State state=null;
  File targetFile=new File(path);
  if (targetFile.canWrite()) {
    return new BaseState(false,AppInfo.PERMISSION_DENIED);
  }
  try {
    FileUtils.moveFile(tmpFile,targetFile);
  }
 catch (  IOException e) {
    return new BaseState(false,AppInfo.IO_ERROR);
  }
  state=new BaseState(true);
  state.putInfo("size",targetFile.length());
  state.putInfo("title",targetFile.getName());
  return state;
}
