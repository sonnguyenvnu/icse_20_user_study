private Map<String,Map<String,List<Object>>> initOptimizationStructure(){
  Map<String,Map<String,List<Object>>> optimizationTermsFilterStructure=new HashMap<>();
  for (  String comparisonId : this.hashJoinComparisonStructure.getComparisons().keySet()) {
    optimizationTermsFilterStructure.put(comparisonId,new HashMap<String,List<Object>>());
  }
  return optimizationTermsFilterStructure;
}
