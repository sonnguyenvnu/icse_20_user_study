public static FindRequestsResult withRequestJournalDisabled(){
  return new FindRequestsResult(Collections.<LoggedRequest>emptyList(),true);
}
