public int effectiveRaSize(){
  int size=0;
  for (int i=0; i < leftArcFeatureAveragedWeights.length; i++) {
    for (    Object feat : rightArcFeatureAveragedWeights[i].keySet()) {
      for (      float f : rightArcFeatureAveragedWeights[i].get(feat).getArray())       if (f != 0f)       size++;
    }
  }
  return size;
}
