@Override public List<NearMiss> findNearMissesFor(LoggedRequest loggedRequest){
  return client.findTopNearMissesFor(loggedRequest);
}
