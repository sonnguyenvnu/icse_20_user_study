public void addLabel(String label){
  Integer frequency=labelMap.get(label);
  if (frequency == null) {
    frequency=1;
  }
 else {
    ++frequency;
  }
  labelMap.put(label,frequency);
}
