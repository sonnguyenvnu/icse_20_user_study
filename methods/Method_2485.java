public static String max(Map<String,Double> scoreMap){
  double max=Double.NEGATIVE_INFINITY;
  String best=null;
  for (  Map.Entry<String,Double> entry : scoreMap.entrySet()) {
    Double score=entry.getValue();
    if (score > max) {
      max=score;
      best=entry.getKey();
    }
  }
  return best;
}
