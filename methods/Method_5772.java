/** 
 * Disables locking the cache folders which  {@link SimpleCache} instances are using and releasesany previous lock. <p>The locking prevents multiple  {@link SimpleCache} instances from being created for the samefolder. Disabling it may cause the cache data to be corrupted. Use at your own risk.
 * @deprecated Don't create multiple {@link SimpleCache} instances for the same cache folder. Ifyou need to create another instance, make sure you call  {@link #release()} on the previousinstance.
 */
@Deprecated public static synchronized void disableCacheFolderLocking(){
  cacheFolderLockingDisabled=true;
  lockedCacheDirs.clear();
}
