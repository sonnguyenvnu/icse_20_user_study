protected boolean isMatcherEquals(final GroupMatcher<?> matcher){
  return matcher.getCompareWithOperator().equals(StringMatcher.StringOperatorName.EQUALS);
}
