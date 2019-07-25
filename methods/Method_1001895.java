public TValue add(TValue v2){
  if (this.isNumber() && v2.isNumber()) {
    return new TValue(this.intValue + v2.intValue);
  }
  return new TValue(toString() + v2.toString());
}
