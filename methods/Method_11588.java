private void compileRegex(String regexStr){
  if (StringUtils.isBlank(regexStr)) {
    throw new IllegalArgumentException("regex must not be empty");
  }
  try {
    this.regex=Pattern.compile(regexStr,Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
    this.regexStr=regexStr;
  }
 catch (  PatternSyntaxException e) {
    throw new IllegalArgumentException("invalid regex " + regexStr,e);
  }
}
