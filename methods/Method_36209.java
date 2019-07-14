@Override public List<LoggedRequest> getRequestsMatching(RequestPattern requestPattern){
  return ImmutableList.copyOf(filter(getRequests(),thatMatch(requestPattern)));
}
