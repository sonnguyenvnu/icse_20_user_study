public void assertRequestJournalEnabled(){
  if (requestJournalDisabled) {
    throw new RequestJournalDisabledException();
  }
}
