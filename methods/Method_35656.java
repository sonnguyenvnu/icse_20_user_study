public List<NearMiss> findTopNearMissesFor(LoggedRequest loggedRequest){
  FindNearMissesResult nearMissesResult=admin.findTopNearMissesFor(loggedRequest);
  return nearMissesResult.getNearMisses();
}
