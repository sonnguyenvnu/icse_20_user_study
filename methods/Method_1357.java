/** 
 * Update stats for a single directory and return the StatFs object for that directory. If the directory does not exist or the StatFs restat() or constructor fails (throws), a null StatFs object is returned.
 */
private @Nullable StatFs updateStatsHelper(@Nullable StatFs statfs,@Nullable File dir){
  if (dir == null || !dir.exists()) {
    return null;
  }
  try {
    if (statfs == null) {
      statfs=createStatFs(dir.getAbsolutePath());
    }
 else {
      statfs.restat(dir.getAbsolutePath());
    }
  }
 catch (  IllegalArgumentException ex) {
    statfs=null;
  }
catch (  Throwable ex) {
    throw Throwables.propagate(ex);
  }
  return statfs;
}
