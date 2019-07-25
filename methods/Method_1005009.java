@Override public boolean test(final HllSketch input){
  if (null == input) {
    return false;
  }
  final long cardinality=Math.round(input.getEstimate());
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
