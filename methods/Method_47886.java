@NonNull private List<Score> groupsToAvgScores(HashMap<Timestamp,ArrayList<Double>> groups){
  List<Score> scores=new LinkedList<>();
  for (  Timestamp timestamp : groups.keySet()) {
    double meanValue=0.0;
    ArrayList<Double> groupValues=groups.get(timestamp);
    for (    Double v : groupValues)     meanValue+=v;
    meanValue/=groupValues.size();
    scores.add(new Score(timestamp,meanValue));
  }
  return scores;
}
