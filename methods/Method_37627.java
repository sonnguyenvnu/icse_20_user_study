/** 
 * Enables usage of provided watch file.
 */
public DirWatcher useWatchFile(final String name){
  watchFile=new File(dir,name);
  if (!watchFile.isFile() || !watchFile.exists()) {
    try {
      FileUtil.touch(watchFile);
    }
 catch (    IOException ioex) {
      throw new DirWatcherException("Invalid watch file: " + name,ioex);
    }
  }
  watchFileLastAccessTime=watchFile.lastModified();
  return this;
}
