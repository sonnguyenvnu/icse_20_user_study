public static List<PathElement> parse(String key){
  if (key.contains(AT)) {
    return Arrays.<PathElement>asList(new AtPathElement(key));
  }
 else   if (STAR.equals(key)) {
    return Arrays.<PathElement>asList(new StarAllPathElement(key));
  }
 else   if (key.contains(STAR)) {
    if (StringTools.countMatches(key,STAR) == 1) {
      return Arrays.<PathElement>asList(new StarSinglePathElement(key));
    }
 else {
      return Arrays.<PathElement>asList(new StarRegexPathElement(key));
    }
  }
 else {
    return Arrays.<PathElement>asList(new LiteralPathElement(key));
  }
}
