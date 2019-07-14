public static List<NearMiss> findNearMissesFor(LoggedRequest loggedRequest){
  return defaultInstance.get().findTopNearMissesFor(loggedRequest);
}
