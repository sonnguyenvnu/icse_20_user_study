private double promote(final byte[] key,final short coupon,final Map fromMap,final int fromIndex,final int fromLevel,final int baseMapIndex,final double estimate){
  final Map newMap=getMapForLevel(fromLevel + 1);
  final int newMapIndex=newMap.findOrInsertKey(key);
  final CouponsIterator it=fromMap.getCouponsIterator(fromIndex);
  while (it.next()) {
    final double est=newMap.update(newMapIndex,it.getValue());
    assert est > 0;
  }
  fromMap.deleteKey(fromIndex);
  newMap.updateEstimate(newMapIndex,estimate);
  final double newEstimate=newMap.update(newMapIndex,coupon);
  setLevelInBaseMap(baseMapIndex,fromLevel + 1);
  assert newEstimate > 0;
  return newEstimate;
}
