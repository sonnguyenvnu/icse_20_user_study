@Override public VerificationResult countRequestsMatching(RequestPattern requestPattern){
  try {
    return VerificationResult.withCount(requestJournal.countRequestsMatching(requestPattern));
  }
 catch (  RequestJournalDisabledException e) {
    return VerificationResult.withRequestJournalDisabled();
  }
}
