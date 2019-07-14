public static RequestPatternBuilder requestMadeFor(ValueMatcher<Request> requestMatcher){
  return RequestPatternBuilder.forCustomMatcher(requestMatcher);
}
