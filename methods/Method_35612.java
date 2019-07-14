public static StringValuePattern notMatching(String regex){
  return new NegativeRegexPattern(regex);
}
