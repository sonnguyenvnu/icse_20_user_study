private double computeEntropy(Set<Map.Entry<String,TriaFrequency>> entrySet){
  double totalFrequency=0;
  for (  Map.Entry<String,TriaFrequency> entry : entrySet) {
    totalFrequency+=entry.getValue().getValue();
  }
  double le=0;
  for (  Map.Entry<String,TriaFrequency> entry : entrySet) {
    double p=entry.getValue().getValue() / totalFrequency;
    le+=-p * Math.log(p);
  }
  return le;
}
