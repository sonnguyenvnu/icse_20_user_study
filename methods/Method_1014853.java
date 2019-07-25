public static PathElement parse(String key){
  if ("*".equals(key)) {
    return new StarAllPathElement(key);
  }
  int numOfStars=StringTools.countMatches(key,"*");
  if (numOfStars == 1) {
    return new StarSinglePathElement(key);
  }
 else   if (numOfStars == 2) {
    return new StarDoublePathElement(key);
  }
 else   if (numOfStars > 2) {
    return new StarRegexPathElement(key);
  }
 else {
    return new LiteralPathElement(key);
  }
}
