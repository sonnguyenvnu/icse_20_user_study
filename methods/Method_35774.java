@Override public FindRequestsResult findUnmatchedRequests(){
  try {
    List<LoggedRequest> requests=from(requestJournal.getAllServeEvents()).filter(NOT_MATCHED).transform(TO_LOGGED_REQUEST).toList();
    return FindRequestsResult.withRequests(requests);
  }
 catch (  RequestJournalDisabledException e) {
    return FindRequestsResult.withRequestJournalDisabled();
  }
}
