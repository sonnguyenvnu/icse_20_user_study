static String reduceRecentFilePath(String path){
  int lastSeparatorPosition=path.lastIndexOf(File.separatorChar);
  if ((lastSeparatorPosition == -1) || (lastSeparatorPosition < Constants.RECENT_FILE_MAX_LENGTH)) {
    return path;
  }
  int length=Constants.RECENT_FILE_MAX_LENGTH / 2 - 2;
  String left=path.substring(0,length);
  String right=path.substring(path.length() - length);
  return left + "..." + right;
}
