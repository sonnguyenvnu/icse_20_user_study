@Override public List<String> selectList(String text){
  List<String> results=new ArrayList<String>();
  boolean first=true;
  for (  Selector selector : selectors) {
    if (first) {
      results=selector.selectList(text);
      first=false;
    }
 else {
      List<String> resultsTemp=new ArrayList<String>();
      for (      String result : results) {
        resultsTemp.addAll(selector.selectList(result));
      }
      results=resultsTemp;
      if (results == null || results.size() == 0) {
        return results;
      }
    }
  }
  return results;
}
