protected void processValue(long v,List<Long> allValues){
  _min=Math.min(_min,v);
  _max=Math.max(_max,v);
  allValues.add(v);
}
