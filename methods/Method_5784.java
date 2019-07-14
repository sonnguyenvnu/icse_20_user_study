/** 
 * Upgrades the cache file if it is created by an earlier version of  {@link SimpleCache}.
 * @param file The cache file.
 * @param index Cached content index.
 * @return Upgraded cache file or {@code null} if the file name is not correctly formatted or thefile can not be renamed.
 */
@Nullable private static File upgradeFile(File file,CachedContentIndex index){
  String key;
  String filename=file.getName();
  Matcher matcher=CACHE_FILE_PATTERN_V2.matcher(filename);
  if (matcher.matches()) {
    key=Util.unescapeFileName(matcher.group(1));
    if (key == null) {
      return null;
    }
  }
 else {
    matcher=CACHE_FILE_PATTERN_V1.matcher(filename);
    if (!matcher.matches()) {
      return null;
    }
    key=matcher.group(1);
  }
  File newCacheFile=getCacheFile(file.getParentFile(),index.assignIdForKey(key),Long.parseLong(matcher.group(2)),Long.parseLong(matcher.group(3)));
  if (!file.renameTo(newCacheFile)) {
    return null;
  }
  return newCacheFile;
}
