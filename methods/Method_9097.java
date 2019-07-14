private int getMeasurement(Child c,boolean horizontal){
  return horizontal ? c.getMeasuredWidth() : c.getMeasuredHeight();
}
