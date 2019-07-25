public void count(){
  IntIntHashMap docCounts=new IntIntHashMap();
  int index=0;
  if (instances.size() == 0) {
    logger.info("Instance list is empty");
    return;
  }
  if (instances.get(0).getData() instanceof FeatureSequence) {
    for (    Instance instance : instances) {
      FeatureSequence features=(FeatureSequence)instance.getData();
      for (int i=0; i < features.getLength(); i++) {
        docCounts.putOrAdd(features.getIndexAtPosition(i),1,1);
      }
      for (      IntCursor cursor : docCounts.keys()) {
        int feature=cursor.value;
        featureCounts[feature]+=docCounts.get(feature);
        documentFrequencies[feature]++;
      }
      docCounts=new IntIntHashMap();
      index++;
      if (index % 1000 == 0) {
        System.err.println(index);
      }
    }
  }
 else   if (instances.get(0).getData() instanceof FeatureVector) {
    for (    Instance instance : instances) {
      FeatureVector features=(FeatureVector)instance.getData();
      for (int location=0; location < features.numLocations(); location++) {
        int feature=features.indexAtLocation(location);
        double value=features.valueAtLocation(location);
        documentFrequencies[feature]++;
        featureCounts[feature]+=value;
      }
      index++;
      if (index % 1000 == 0) {
        System.err.println(index);
      }
    }
  }
 else {
    logger.info("Unsupported data class: " + instances.get(0).getData().getClass().getName());
  }
}
