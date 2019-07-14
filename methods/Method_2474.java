/** 
 * ????????????????<br> https://nlp.stanford.edu/IR-book/html/htmledition/feature-selectionchi2-feature-selection-1.html
 * @param stats
 * @return
 */
public Map<Integer,Double> chi_square(BaseFeatureData stats){
  Map<Integer,Double> selectedFeatures=new HashMap<Integer,Double>();
  double N1dot, N0dot, N00, N01, N10, N11;
  double chisquareScore;
  Double previousScore;
  for (int feature=0; feature < stats.featureCategoryJointCount.length; feature++) {
    int[] categoryList=stats.featureCategoryJointCount[feature];
    N1dot=0;
    for (    int count : categoryList) {
      N1dot+=count;
    }
    N0dot=stats.n - N1dot;
    for (int category=0; category < categoryList.length; category++) {
      N11=categoryList[category];
      N01=stats.categoryCounts[category] - N11;
      N00=N0dot - N01;
      N10=N1dot - N11;
      chisquareScore=stats.n * Math.pow(N11 * N00 - N10 * N01,2) / ((N11 + N01) * (N11 + N10) * (N10 + N00) * (N01 + N00));
      if (chisquareScore >= chisquareCriticalValue) {
        previousScore=selectedFeatures.get(feature);
        if (previousScore == null || chisquareScore > previousScore) {
          selectedFeatures.put(feature,chisquareScore);
        }
      }
    }
  }
  if (selectedFeatures.size() == 0) {
    for (int feature=0; feature < stats.featureCategoryJointCount.length; feature++) {
      selectedFeatures.put(feature,0.);
    }
  }
  if (selectedFeatures.size() > maxSize) {
    MaxHeap<Map.Entry<Integer,Double>> maxHeap=new MaxHeap<Map.Entry<Integer,Double>>(maxSize,new Comparator<Map.Entry<Integer,Double>>(){
      @Override public int compare(      Map.Entry<Integer,Double> o1,      Map.Entry<Integer,Double> o2){
        return o1.getValue().compareTo(o2.getValue());
      }
    }
);
    for (    Map.Entry<Integer,Double> entry : selectedFeatures.entrySet()) {
      maxHeap.add(entry);
    }
    selectedFeatures.clear();
    for (    Map.Entry<Integer,Double> entry : maxHeap) {
      selectedFeatures.put(entry.getKey(),entry.getValue());
    }
  }
  return selectedFeatures;
}
