public int raSize(){
  int size=0;
  for (int i=0; i < leftArcFeatureAveragedWeights.length; i++) {
    for (    Object feat : rightArcFeatureAveragedWeights[i].keySet()) {
      size+=rightArcFeatureAveragedWeights[i].get(feat).length();
    }
  }
  return size;
}
