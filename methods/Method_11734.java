private String computeCommonPrefix(){
  return (fPrefix > fContextLength ? ELLIPSIS : "") + fExpected.substring(Math.max(0,fPrefix - fContextLength),fPrefix);
}
