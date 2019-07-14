private void initFeatureMatrix(String[] termArray,FeatureMap featureMap){
  featureMatrix=new int[termArray.length][];
  for (int i=0; i < featureMatrix.length; i++) {
    featureMatrix[i]=extractFeature(termArray,featureMap,i);
  }
}
