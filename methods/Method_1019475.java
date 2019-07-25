@Override HllSketchImpl reset(){
  return new CouponList(lgConfigK,tgtHllType,CurMode.LIST);
}
