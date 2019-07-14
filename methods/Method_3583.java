@Override public List<String> suggest(String key,int size){
  List<String> resultList=new ArrayList<String>(size);
  TreeMap<String,Double> scoreMap=new TreeMap<String,Double>();
  for (  BaseScorer scorer : scorerList) {
    Map<String,Double> map=scorer.computeScore(key);
    Double max=max(map);
    for (    Map.Entry<String,Double> entry : map.entrySet()) {
      Double score=scoreMap.get(entry.getKey());
      if (score == null)       score=0.0;
      scoreMap.put(entry.getKey(),score / max + entry.getValue() * scorer.boost);
    }
  }
  for (  Map.Entry<Double,Set<String>> entry : sortScoreMap(scoreMap).entrySet()) {
    for (    String sentence : entry.getValue()) {
      if (resultList.size() >= size)       return resultList;
      resultList.add(sentence);
    }
  }
  return resultList;
}
