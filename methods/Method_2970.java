public int effectiveLaSize(){
  int size=0;
  for (int i=0; i < leftArcFeatureAveragedWeights.length; i++) {
    for (    Object feat : leftArcFeatureAveragedWeights[i].keySet()) {
      for (      float f : leftArcFeatureAveragedWeights[i].get(feat).getArray())       if (f != 0f)       size++;
    }
  }
  return size;
}
