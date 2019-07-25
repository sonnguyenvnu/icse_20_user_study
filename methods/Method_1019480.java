@Override double update(final int entryIndex,final short coupon){
  final int couponMapArrEntryIndex=entryIndex * maxCouponsPerKey_;
  int innerCouponIndex=(coupon & 0xFFFF) % maxCouponsPerKey_;
  while (couponsArr_[couponMapArrEntryIndex + innerCouponIndex] != 0) {
    if (couponsArr_[couponMapArrEntryIndex + innerCouponIndex] == coupon) {
      return hipEstAccumArr_[entryIndex];
    }
    innerCouponIndex=(innerCouponIndex + 1) % maxCouponsPerKey_;
  }
  if (((curCountsArr_[entryIndex] + 1) & BYTE_MASK) > capacityCouponsPerKey_) {
    return -hipEstAccumArr_[entryIndex];
  }
  couponsArr_[couponMapArrEntryIndex + innerCouponIndex]=coupon;
  curCountsArr_[entryIndex]++;
  hipEstAccumArr_[entryIndex]+=COUPON_K / invPow2SumArr_[entryIndex];
  invPow2SumArr_[entryIndex]-=invPow2(coupon16Value(coupon));
  return hipEstAccumArr_[entryIndex];
}
