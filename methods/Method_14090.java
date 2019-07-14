protected boolean processValue(double v,List<Double> allValues){
  if (!Double.isInfinite(v) && !Double.isNaN(v)) {
    _min=Math.min(_min,v);
    _max=Math.max(_max,v);
    allValues.add(v);
    return true;
  }
 else {
    return false;
  }
}
