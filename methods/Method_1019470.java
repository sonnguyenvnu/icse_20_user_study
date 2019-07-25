@Override HllSketchImpl reset(){
  if (wmem == null) {
    throw new SketchesArgumentException("Cannot reset a read-only sketch");
  }
  final int bytes=HllSketch.getMaxUpdatableSerializationBytes(lgConfigK,tgtHllType);
  wmem.clear(0,bytes);
  return DirectCouponList.newInstance(lgConfigK,tgtHllType,wmem);
}
