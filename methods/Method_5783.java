/** 
 * Creates a cache span from an underlying cache file. Upgrades the file if necessary.
 * @param file The cache file.
 * @param length The length of the cache file in bytes.
 * @return The span, or null if the file name is not correctly formatted, or if the id is notpresent in the content index.
 */
@Nullable public static SimpleCacheSpan createCacheEntry(File file,long length,CachedContentIndex index){
  String name=file.getName();
  if (!name.endsWith(SUFFIX)) {
    file=upgradeFile(file,index);
    if (file == null) {
      return null;
    }
    name=file.getName();
  }
  Matcher matcher=CACHE_FILE_PATTERN_V3.matcher(name);
  if (!matcher.matches()) {
    return null;
  }
  int id=Integer.parseInt(matcher.group(1));
  String key=index.getKeyForId(id);
  return key == null ? null : new SimpleCacheSpan(key,Long.parseLong(matcher.group(2)),length,Long.parseLong(matcher.group(3)),file);
}
