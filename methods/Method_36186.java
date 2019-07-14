private boolean requestNotAlreadyReceived(RequestPattern requestPattern){
  VerificationResult verificationResult=admin.countRequestsMatching(requestPattern);
  verificationResult.assertRequestJournalEnabled();
  return (verificationResult.getCount() < 1);
}
