/** 
 * ???map????
 * @param scoreMap
 * @return
 */
private static TreeMap<Double,Set<String>> sortScoreMap(TreeMap<String,Double> scoreMap){
  TreeMap<Double,Set<String>> result=new TreeMap<Double,Set<String>>(Collections.reverseOrder());
  for (  Map.Entry<String,Double> entry : scoreMap.entrySet()) {
    Set<String> sentenceSet=result.get(entry.getValue());
    if (sentenceSet == null) {
      sentenceSet=new HashSet<String>();
      result.put(entry.getValue(),sentenceSet);
    }
    sentenceSet.add(entry.getKey());
  }
  return result;
}
