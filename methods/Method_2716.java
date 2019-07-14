public int getFrequency(E label){
  Integer frequency=labelMap.get(label);
  if (frequency == null)   return 0;
  return frequency;
}
