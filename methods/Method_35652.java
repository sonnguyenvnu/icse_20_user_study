public List<NearMiss> findNearMissesForAllUnmatchedRequests(){
  FindNearMissesResult nearMissesResult=admin.findNearMissesForUnmatchedRequests();
  return nearMissesResult.getNearMisses();
}
