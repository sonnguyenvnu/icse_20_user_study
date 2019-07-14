/** 
 * ?????????????????FeatureStats?????????????
 * @param dataSet
 * @return
 */
protected BaseFeatureData selectFeatures(IDataSet dataSet){
  ChiSquareFeatureExtractor chiSquareFeatureExtractor=new ChiSquareFeatureExtractor();
  logger.start("???????????...");
  BaseFeatureData featureData=chiSquareFeatureExtractor.extractBasicFeatureData(dataSet);
  Map<Integer,Double> selectedFeatures=chiSquareFeatureExtractor.chi_square(featureData);
  int[][] featureCategoryJointCount=new int[selectedFeatures.size()][];
  featureData.wordIdTrie=new BinTrie<Integer>();
  String[] wordIdArray=dataSet.getLexicon().getWordIdArray();
  int p=-1;
  for (  Integer feature : selectedFeatures.keySet()) {
    featureCategoryJointCount[++p]=featureData.featureCategoryJointCount[feature];
    featureData.wordIdTrie.put(wordIdArray[feature],p);
  }
  logger.finish(",?????:%d / %d = %.2f%%\n",featureCategoryJointCount.length,featureData.featureCategoryJointCount.length,featureCategoryJointCount.length / (double)featureData.featureCategoryJointCount.length * 100.);
  featureData.featureCategoryJointCount=featureCategoryJointCount;
  return featureData;
}
