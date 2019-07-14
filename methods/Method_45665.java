/** 
 * ?????????
 * @param consumerToProvider ?????true????????false
 * @return ???????
 */
public Short getAvailableRefIndex(boolean consumerToProvider){
  if (headerCache == null) {
synchronized (this) {
      if (headerCache == null) {
        headerCache=new TwoWayMap<Short,String>();
      }
    }
  }
  if (consumerToProvider) {
    for (short i=0; i < Short.MAX_VALUE; i++) {
      if (!headerCache.containsKey(i)) {
        return i;
      }
    }
  }
 else {
    for (short i=-1; i > Short.MIN_VALUE; i--) {
      if (!headerCache.containsKey(i)) {
        return i;
      }
    }
  }
  return null;
}
