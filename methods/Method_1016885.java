public int put(int featureIndex,int value){
  if (featureIndex < 0 || featureIndex > alphabet.size())   throw new IllegalArgumentException("featureIndex " + featureIndex + " out of range");
  return featureCounts.put(featureIndex,value);
}
