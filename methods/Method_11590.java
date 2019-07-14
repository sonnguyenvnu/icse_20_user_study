public RegexResult selectGroup(String text){
  Matcher matcher=regex.matcher(text);
  if (matcher.find()) {
    String[] groups=new String[matcher.groupCount() + 1];
    for (int i=0; i < groups.length; i++) {
      groups[i]=matcher.group(i);
    }
    return new RegexResult(groups);
  }
  return RegexResult.EMPTY_RESULT;
}
