public static MatcherSet build(MatcherType ruleSet){
  if (ruleSet == MatcherType.AND) {
    return new AndMatcher();
  }
 else {
    return new OrMatcher();
  }
}
