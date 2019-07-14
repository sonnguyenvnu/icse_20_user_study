@Override public List<LoggedRequest> getRequestsMatching(RequestPattern requestPattern){
  throw new RequestJournalDisabledException();
}
