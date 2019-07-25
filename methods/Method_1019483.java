/** 
 * Updates the map with a given key and identifier and returns the estimate of the number of unique identifiers encountered so far for the given key.
 * @param key the given key
 * @param identifier the given identifier for unique counting associated with the key
 * @return the estimate of the number of unique identifiers encountered so far for the given key.
 */
public double update(final byte[] key,final byte[] identifier){
  if (key == null) {
    return Double.NaN;
  }
  checkMethodKeySize(key);
  if (identifier == null) {
    return getEstimate(key);
  }
  final short coupon=(short)Map.coupon16(identifier);
  final int baseMapIndex=maps_[0].findOrInsertKey(key);
  final double baseMapEstimate=maps_[0].update(baseMapIndex,coupon);
  if (baseMapEstimate > 0) {
    return baseMapEstimate;
  }
  final int level=-(int)baseMapEstimate;
  if (level == 0) {
    return promote(key,coupon,maps_[0],baseMapIndex,level,baseMapIndex,0);
  }
  final Map map=maps_[level];
  final int index=map.findOrInsertKey(key);
  final double estimate=map.update(index,coupon);
  if (estimate > 0) {
    return estimate;
  }
  return promote(key,coupon,map,index,level,baseMapIndex,-estimate);
}
