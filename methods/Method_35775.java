@Override public FindNearMissesResult findNearMissesForUnmatchedRequests(){
  ImmutableList.Builder<NearMiss> listBuilder=ImmutableList.builder();
  Iterable<ServeEvent> unmatchedServeEvents=from(requestJournal.getAllServeEvents()).filter(new Predicate<ServeEvent>(){
    @Override public boolean apply(    ServeEvent input){
      return input.isNoExactMatch();
    }
  }
);
  for (  ServeEvent serveEvent : unmatchedServeEvents) {
    listBuilder.addAll(nearMissCalculator.findNearestTo(serveEvent.getRequest()));
  }
  return new FindNearMissesResult(listBuilder.build());
}
