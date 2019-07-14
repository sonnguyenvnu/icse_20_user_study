/** 
 * ?????????
 * @param context
 * @return
 */
public final Pair<String,Double> predictBest(String[] context){
  List<Pair<String,Double>> resultList=predict(context);
  double bestP=-1.0;
  Pair<String,Double> bestPair=null;
  for (  Pair<String,Double> pair : resultList) {
    if (pair.getSecond() > bestP) {
      bestP=pair.getSecond();
      bestPair=pair;
    }
  }
  return bestPair;
}
