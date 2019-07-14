/** 
 * Clears all settings and removes all created bundle files from file system.
 */
public synchronized void reset(){
  if (strategy == Strategy.ACTION_MANAGED) {
    actionBundles.clear();
    mirrors.clear();
  }
  final FindFile ff=new FindFile();
  ff.includeDirs(false);
  ff.searchPath(new File(bundleFolder,staplerPath));
  File f;
  int count=0;
  while ((f=ff.nextFile()) != null) {
    f.delete();
    count++;
  }
  if (log.isInfoEnabled()) {
    log.info("reset: " + count + " bundle files deleted.");
  }
}
