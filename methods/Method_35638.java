public void verifyThat(CountMatchingStrategy expectedCount,RequestPatternBuilder requestPatternBuilder){
  final RequestPattern requestPattern=requestPatternBuilder.build();
  int actualCount;
  if (requestPattern.hasInlineCustomMatcher()) {
    List<LoggedRequest> requests=admin.findRequestsMatching(RequestPattern.everything()).getRequests();
    actualCount=from(requests).filter(thatMatch(requestPattern)).size();
  }
 else {
    VerificationResult result=admin.countRequestsMatching(requestPattern);
    result.assertRequestJournalEnabled();
    actualCount=result.getCount();
  }
  if (!expectedCount.match(actualCount)) {
    throw actualCount == 0 ? verificationExceptionForNearMisses(requestPatternBuilder,requestPattern) : new VerificationException(requestPattern,expectedCount,actualCount);
  }
}
