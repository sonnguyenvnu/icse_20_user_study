public float shiftScore(final Object[] features,boolean decode){
  float score=0.0f;
  HashMap<Object,Float>[] map=decode ? shiftFeatureAveragedWeights : shiftFeatureWeights;
  for (int i=0; i < features.length; i++) {
    if (features[i] == null || (i >= 26 && i < 32))     continue;
    Float weight=map[i].get(features[i]);
    if (weight != null) {
      score+=weight;
    }
  }
  return score;
}
