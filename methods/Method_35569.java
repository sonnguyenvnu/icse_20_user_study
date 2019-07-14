@Override public VerificationResult countRequestsMatching(RequestPattern requestPattern){
  String body=postJsonAssertOkAndReturnBody(urlFor(GetRequestCountTask.class),Json.write(requestPattern));
  return VerificationResult.from(body);
}
