@Override public MockResponse dispatch(RecordedRequest recordedRequest) throws InterruptedException {
  requestsHistory.add(recordedRequest);
  RESTMockServer.getLogger().log("-> New Request:\t" + recordedRequest);
  List<MatchableCall> matchedCalls=getMatchedRequests(recordedRequest);
  if (matchedCalls.size() == 1) {
    return onOneResponseMatched(recordedRequest,matchedCalls);
  }
 else   if (matchedCalls.size() > 1) {
    return onTooManyResponsesMatched(recordedRequest,matchedCalls);
  }
 else {
    return onNoResponsesMatched(recordedRequest);
  }
}
