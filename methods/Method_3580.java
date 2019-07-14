@Override public Double similarity(PinyinKey other){
  int firstCharArrayLength=firstCharArray.length + 1;
  return 1.0 / (EditDistance.compute(pyOrdinalArray,other.pyOrdinalArray) + 1) + (double)LongestCommonSubstring.compute(firstCharArray,other.firstCharArray) / firstCharArrayLength;
}
