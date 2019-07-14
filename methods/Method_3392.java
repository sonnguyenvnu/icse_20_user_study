protected static int[] toFeatureArray(List<Integer> featureVector){
  int[] featureArray=new int[featureVector.size() + 1];
  int index=-1;
  for (  Integer feature : featureVector) {
    featureArray[++index]=feature;
  }
  return featureArray;
}
