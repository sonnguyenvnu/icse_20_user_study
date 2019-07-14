/** 
 * ???????.
 * @param listener ?????
 */
public void addDataListener(final TreeCacheListener listener){
  TreeCache cache=(TreeCache)regCenter.getRawCache("/" + jobName);
  cache.getListenable().addListener(listener);
}
