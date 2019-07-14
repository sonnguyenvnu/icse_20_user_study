private long getDirectorySize(File dir,int documentsMusicType){
  if (dir == null || canceled) {
    return 0;
  }
  long size=0;
  if (dir.isDirectory()) {
    size=Utilities.getDirSize(dir.getAbsolutePath(),documentsMusicType);
  }
 else   if (dir.isFile()) {
    size+=dir.length();
  }
  return size;
}
