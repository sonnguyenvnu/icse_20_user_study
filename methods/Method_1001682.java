public MinMax add(int value){
  final int newMin=Math.min(min,value);
  final int newMax=Math.max(max,value);
  if (min == newMin && max == newMax) {
    return this;
  }
  return new MinMax(newMin,newMax);
}
