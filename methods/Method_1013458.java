public final boolean assignable(Value val){
  try {
    return ((val instanceof IntervalValue) && this.high == ((IntervalValue)val).high && this.low == ((IntervalValue)val).low);
  }
 catch (  RuntimeException|OutOfMemoryError e) {
    if (hasSource()) {
      throw FingerprintException.getNewHead(this,e);
    }
 else {
      throw e;
    }
  }
}
