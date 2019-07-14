/** 
 * ??log-sum-exp???????????
 * @param predictionScores
 */
public static void normalizeExp(Map<String,Double> predictionScores){
  Set<Map.Entry<String,Double>> entrySet=predictionScores.entrySet();
  double max=Double.NEGATIVE_INFINITY;
  for (  Map.Entry<String,Double> entry : entrySet) {
    max=Math.max(max,entry.getValue());
  }
  double sum=0.0;
  for (  Map.Entry<String,Double> entry : entrySet) {
    Double value=Math.exp(entry.getValue() - max);
    entry.setValue(value);
    sum+=value;
  }
  if (sum != 0.0) {
    for (    Map.Entry<String,Double> entry : entrySet) {
      predictionScores.put(entry.getKey(),entry.getValue() / sum);
    }
  }
}
