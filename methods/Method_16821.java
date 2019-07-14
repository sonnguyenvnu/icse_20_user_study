private static State valid(File file){
  File parentPath=file.getParentFile();
  if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
    return new BaseState(false,AppInfo.FAILED_CREATE_FILE);
  }
  if (!parentPath.canWrite()) {
    return new BaseState(false,AppInfo.PERMISSION_DENIED);
  }
  return new BaseState(true);
}
