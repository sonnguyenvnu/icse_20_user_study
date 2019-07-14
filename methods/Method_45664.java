/** 
 * Put header cache
 * @param key   the key
 * @param value the value
 */
public void putHeadCache(Short key,String value){
  if (headerCache == null) {
synchronized (this) {
      if (headerCache == null) {
        headerCache=new TwoWayMap<Short,String>();
      }
    }
  }
  if (headerCache != null && !headerCache.containsKey(key)) {
    headerCache.put(key,value);
  }
}
