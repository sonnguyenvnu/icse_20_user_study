public float[] leftArcScores(final Object[] features,boolean decode){
  float scores[]=new float[dependencySize];
  HashMap<Object,CompactArray>[] map=decode ? leftArcFeatureAveragedWeights : leftArcFeatureWeights;
  for (int i=0; i < features.length; i++) {
    if (features[i] == null)     continue;
    CompactArray values=map[i].get(features[i]);
    if (values != null) {
      int offset=values.getOffset();
      float[] weightVector=values.getArray();
      for (int d=offset; d < offset + weightVector.length; d++) {
        scores[d]+=weightVector[d - offset];
      }
    }
  }
  return scores;
}
