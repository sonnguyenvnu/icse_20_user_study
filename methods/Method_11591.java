public List<RegexResult> selectGroupList(String text){
  Matcher matcher=regex.matcher(text);
  List<RegexResult> resultList=new ArrayList<RegexResult>();
  while (matcher.find()) {
    String[] groups=new String[matcher.groupCount() + 1];
    for (int i=0; i < groups.length; i++) {
      groups[i]=matcher.group(i);
    }
    resultList.add(new RegexResult(groups));
  }
  return resultList;
}
