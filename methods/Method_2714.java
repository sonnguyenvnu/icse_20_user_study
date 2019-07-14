public void addLabel(E label,Integer frequency){
  Integer innerFrequency=labelMap.get(label);
  if (innerFrequency == null) {
    innerFrequency=frequency;
  }
 else {
    innerFrequency+=frequency;
  }
  labelMap.put(label,innerFrequency);
}
