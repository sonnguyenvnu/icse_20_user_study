@Override public FindNearMissesResult findTopNearMissesFor(LoggedRequest loggedRequest){
  String body=postJsonAssertOkAndReturnBody(urlFor(FindNearMissesForRequestTask.class),Json.write(loggedRequest));
  return Json.read(body,FindNearMissesResult.class);
}
