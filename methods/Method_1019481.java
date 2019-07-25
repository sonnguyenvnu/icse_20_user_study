@Override double update(final int entryIndex,final short value){
  final int offset=entryIndex * maxCouponsPerKey_;
  boolean wasFound=false;
  for (int i=0; i < maxCouponsPerKey_; i++) {
    if (couponsArr_[offset + i] == 0) {
      if (wasFound) {
        return i;
      }
      couponsArr_[offset + i]=value;
      return i + 1;
    }
    if (couponsArr_[offset + i] == value) {
      wasFound=true;
    }
  }
  if (wasFound) {
    return maxCouponsPerKey_;
  }
  return -maxCouponsPerKey_;
}
