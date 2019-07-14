@Override public FindNearMissesResult findTopNearMissesFor(LoggedRequest loggedRequest){
  return new FindNearMissesResult(nearMissCalculator.findNearestTo(loggedRequest));
}
