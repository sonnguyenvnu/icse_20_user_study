@Override public FindRequestsResult findRequestsMatching(RequestPattern requestPattern){
  try {
    List<LoggedRequest> requests=requestJournal.getRequestsMatching(requestPattern);
    return FindRequestsResult.withRequests(requests);
  }
 catch (  RequestJournalDisabledException e) {
    return FindRequestsResult.withRequestJournalDisabled();
  }
}
