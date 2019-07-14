private int getMaxFreq(HashMap<Timestamp,Integer[]> frequency){
  int maxValue=1;
  for (  Integer[] values : frequency.values())   for (  Integer value : values)   maxValue=Math.max(value,maxValue);
  return maxValue;
}
