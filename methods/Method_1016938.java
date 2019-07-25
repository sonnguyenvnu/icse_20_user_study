public void count(){
  IntIntHashMap featureCounts=new IntIntHashMap();
  int index=0;
  for (  Instance instance : instances) {
    FeatureSequence features=(FeatureSequence)instance.getData();
    for (int i=0; i < features.getLength(); i++) {
      featureCounts.putOrAdd(features.getIndexAtPosition(i),1,1);
    }
    int[] keys=featureCounts.keys().toArray();
    for (int i=0; i < keys.length - 1; i++) {
      int leftFeature=keys[i];
      for (int j=i + 1; j < keys.length; j++) {
        int rightFeature=keys[j];
        featureFeatureCounts[leftFeature].putOrAdd(rightFeature,1,1);
        featureFeatureCounts[rightFeature].putOrAdd(leftFeature,1,1);
      }
    }
    for (    int key : keys) {
      documentFrequencies[key]++;
    }
    featureCounts=new IntIntHashMap();
    index++;
    if (index % 1000 == 0) {
      System.err.println(index);
    }
  }
}
