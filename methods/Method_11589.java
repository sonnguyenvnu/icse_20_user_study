@Override public List<String> selectList(String text){
  List<String> strings=new ArrayList<String>();
  List<RegexResult> results=selectGroupList(text);
  for (  RegexResult result : results) {
    strings.add(result.get(group));
  }
  return strings;
}
