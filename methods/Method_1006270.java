public static KeywordList parse(String keywordString,Character delimiter,Character hierarchicalDelimiter){
  if (StringUtil.isBlank(keywordString)) {
    return new KeywordList();
  }
  Objects.requireNonNull(delimiter);
  Objects.requireNonNull(hierarchicalDelimiter);
  KeywordList keywordList=new KeywordList();
  StringTokenizer tok=new StringTokenizer(keywordString,delimiter.toString());
  while (tok.hasMoreTokens()) {
    String chain=tok.nextToken();
    Keyword chainRoot=Keyword.of(chain.split(hierarchicalDelimiter.toString()));
    keywordList.add(chainRoot);
  }
  return keywordList;
}
