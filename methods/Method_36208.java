@Override public int countRequestsMatching(RequestPattern requestPattern){
  return size(filter(getRequests(),thatMatch(requestPattern)));
}
