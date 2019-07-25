private void save(List<List<String>> lists){
  StringBuilder sb=new StringBuilder();
  boolean firstList=true;
  for (  List<String> list : lists) {
    if (firstList)     firstList=false;
 else     sb.append(DELIM1);
    boolean firstName=true;
    for (    String name : list) {
      if (firstName)       firstName=false;
 else       sb.append(DELIM2);
      sb.append(name);
    }
  }
  String order=sb.length() == 0 ? null : sb.toString();
  prefs.edit().putString(Prefs.QUEST_ORDER,order).apply();
}
