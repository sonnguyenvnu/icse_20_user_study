@Override double update(final int entryIndex,final short coupon){
  if (couponsArr_[entryIndex] == 0) {
    couponsArr_[entryIndex]=coupon;
    return 1;
  }
  if (isCoupon(entryIndex)) {
    if (couponsArr_[entryIndex] == coupon) {
      return 1;
    }
    return 0;
  }
  return -couponsArr_[entryIndex];
}
