@Override public boolean test(final HyperLogLogPlus input){
  if (null == input) {
    return false;
  }
  final long cardinality=input.cardinality();
  if (orEqualTo) {
    if (cardinality <= controlValue) {
      return true;
    }
  }
 else {
    if (cardinality < controlValue) {
      return true;
    }
  }
  return false;
}
