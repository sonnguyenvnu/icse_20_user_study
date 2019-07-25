@Override protected List<MapTuple> join(final Iterable keys,final String keyName,final String matchingValuesName,final Match match,final Boolean flatten){
  List<MapTuple> resultList=new ArrayList<>();
  for (  final Object keyObj : keys) {
    List matching=match.matching(keyObj);
    MapTuple<String> tuple=new MapTuple<>();
    tuple.put(keyName,keyObj);
    if (flatten) {
      for (      final Object matched : matching) {
        tuple.put(matchingValuesName,matched);
        resultList.add(tuple);
      }
    }
 else     if (!matching.isEmpty()) {
      tuple.put(matchingValuesName,matching);
      resultList.add(tuple);
    }
  }
  return resultList;
}
