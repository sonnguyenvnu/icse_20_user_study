@Override public SingleServedStubResult getServedStub(UUID id){
  return SingleServedStubResult.fromOptional(requestJournal.getServeEvent(id));
}
