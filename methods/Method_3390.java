protected void initFeatureMatrix(String sentence,FeatureMap featureMap){
  featureMatrix=new int[sentence.length()][];
  for (int i=0; i < sentence.length(); i++) {
    featureMatrix[i]=extractFeature(sentence,featureMap,i);
  }
}
