private String computeCommonSuffix(){
  int end=Math.min(fExpected.length() - fSuffix + 1 + fContextLength,fExpected.length());
  return fExpected.substring(fExpected.length() - fSuffix + 1,end) + (fExpected.length() - fSuffix + 1 < fExpected.length() - fContextLength ? ELLIPSIS : "");
}
