@Override public void compile(String pattern){
  this.pattern=pattern;
  String[] split=pattern.split("/");
  Assert.isTrue(!ObjectUtils.isEmpty(split),"invalid pattern given " + pattern);
  hasPattern=pattern.contains("{") && pattern.contains("}");
  index=parse(split[0].trim());
  if (split.length > 1) {
    Assert.isTrue(split.length == 2,"invalid pattern given " + pattern);
    type=parse(split[1].trim());
  }
 else {
    type=null;
  }
}
