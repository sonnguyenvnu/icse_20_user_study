/** 
 * Add objects to the ring with the specified number of points.
 */
@SuppressWarnings("unchecked") protected void add(Map<T,Integer> pointMap){
  for (  Entry<T,Integer> point : pointMap.entrySet()) {
    T t=point.getKey();
    int points=point.getValue();
    if (t == null) {
      warn(_log,"tried to add a null value to consistent hash ring");
      throw new NullPointerException("null values in hash ring are unsupported");
    }
    byte[] bytesToHash=t.toString().getBytes(UTF8);
    byte[] hash=null;
    for (int i=0; i < points; ++i) {
      int iMod4=i % 4;
      int iMod4TimesFour=iMod4 * 4;
      if (iMod4 == 0) {
        hash=_md.digest(bytesToHash);
        bytesToHash=hash;
      }
      int hashInt=hash[iMod4TimesFour] + (hash[iMod4TimesFour + 1] << 8) + (hash[iMod4TimesFour + 2] << 16) + (hash[iMod4TimesFour + 3] << 24);
      _points.add(new Point<T>(t,hashInt));
    }
  }
  Collections.sort(_points);
  debug(_log,"re-initializing consistent hash ring with items: ",_points);
}
