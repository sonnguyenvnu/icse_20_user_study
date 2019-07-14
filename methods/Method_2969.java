public int laSize(){
  int size=0;
  for (int i=0; i < leftArcFeatureAveragedWeights.length; i++) {
    for (    Object feat : leftArcFeatureAveragedWeights[i].keySet()) {
      size+=leftArcFeatureAveragedWeights[i].get(feat).length();
    }
  }
  return size;
}
