private List<List<String>> load(){
  String order=prefs.getString(Prefs.QUEST_ORDER,null);
  if (order != null) {
    String[] lists=order.split(DELIM1);
    List<List<String>> result=new ArrayList<>(lists.length);
    for (    String list : lists) {
      result.add(new ArrayList<>(Arrays.asList(list.split(DELIM2))));
    }
    return result;
  }
  return new ArrayList<>();
}
