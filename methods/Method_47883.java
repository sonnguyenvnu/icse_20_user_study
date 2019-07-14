public List<Score> groupBy(DateUtils.TruncateField field){
  computeAll();
  HashMap<Timestamp,ArrayList<Double>> groups=getGroupedValues(field);
  List<Score> scores=groupsToAvgScores(groups);
  Collections.sort(scores,(s1,s2) -> s2.compareNewer(s1));
  return scores;
}
