@Override public String format(String s){
  if (s == null) {
    return "";
  }
  StringBuilder sb=new StringBuilder();
  KeywordList keywords=KeywordList.parse(s,',');
  int i=0;
  for (  Keyword keyword : keywords) {
    sb.append("KW  - ");
    sb.append(keyword.toString());
    if (i < (keywords.size() - 1)) {
      sb.append(OS.NEWLINE);
    }
    i++;
  }
  return sb.toString();
}
