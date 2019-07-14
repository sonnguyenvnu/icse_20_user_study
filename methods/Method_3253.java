/** 
 * ??????????
 * @param table
 * @param current
 * @return
 */
protected LinkedList<double[]> computeScoreList(Table table,int current){
  LinkedList<double[]> scoreList=new LinkedList<double[]>();
  for (  FeatureTemplate featureTemplate : featureTemplateList) {
    char[] o=featureTemplate.generateParameter(table,current);
    FeatureFunction featureFunction=featureFunctionTrie.get(o);
    if (featureFunction == null)     continue;
    scoreList.add(featureFunction.w);
  }
  return scoreList;
}
