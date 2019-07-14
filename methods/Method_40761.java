public boolean isFalse(){
  if (this == FALSE) {
    return true;
  }
  if (this == TRUE || this.isUndecidedBool()) {
    return false;
  }
  if (this.isIntType() && this.asIntType().isZero()) {
    return true;
  }
  if (this.isFloatType() && this.asFloatType().isZero()) {
    return true;
  }
  if (this == NIL) {
    return true;
  }
  return false;
}
