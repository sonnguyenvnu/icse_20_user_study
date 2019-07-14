public static FMeasure evaluate(IClassifier classifier,Map<String,String[]> testingDataSet){
  return evaluate(classifier,new MemoryDataSet(classifier.getModel()).add(testingDataSet));
}
