/** 
 * Checks if we have to recreate rootDirectory. This is needed because old versions of this storage created too much different files in the same dir, and Samsung's RFS has a bug that after the 13.000th creation fails. So if cache is not already in expected version let's destroy everything (if not in expected version... there's nothing to reuse here anyway).
 */
private void recreateDirectoryIfVersionChanges(){
  boolean recreateBase=false;
  if (!mRootDirectory.exists()) {
    recreateBase=true;
  }
 else   if (!mVersionDirectory.exists()) {
    recreateBase=true;
    FileTree.deleteRecursively(mRootDirectory);
  }
  if (recreateBase) {
    try {
      FileUtils.mkdirs(mVersionDirectory);
    }
 catch (    FileUtils.CreateDirectoryException e) {
      mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.WRITE_CREATE_DIR,TAG,"version directory could not be created: " + mVersionDirectory,null);
    }
  }
}
