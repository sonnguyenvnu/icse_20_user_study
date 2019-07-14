@Override public FindNearMissesResult findTopNearMissesFor(RequestPattern requestPattern){
  String body=postJsonAssertOkAndReturnBody(urlFor(FindNearMissesForRequestPatternTask.class),Json.write(requestPattern));
  return Json.read(body,FindNearMissesResult.class);
}
