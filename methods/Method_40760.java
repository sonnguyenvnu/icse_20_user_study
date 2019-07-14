public boolean isTrue(){
  if (this == TRUE) {
    return true;
  }
  if (this == FALSE || this.isUndecidedBool()) {
    return false;
  }
  if (this.isIntType() && (this.asIntType().lt(BigInteger.ZERO) || this.asIntType().gt(BigInteger.ZERO))) {
    return true;
  }
  if (this.isIntType() && this.asIntType().isZero()) {
    return false;
  }
  if (this.isFloatType() && (this.asFloatType().lt(0) || this.asFloatType().gt(0))) {
    return true;
  }
  if (this.isFloatType() && this.asFloatType().isZero()) {
    return false;
  }
  if (this != NIL) {
    return true;
  }
  return false;
}
