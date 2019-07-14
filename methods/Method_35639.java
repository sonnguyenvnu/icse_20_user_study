private VerificationException verificationExceptionForNearMisses(RequestPatternBuilder requestPatternBuilder,RequestPattern requestPattern){
  List<NearMiss> nearMisses=findAllNearMissesFor(requestPatternBuilder);
  if (nearMisses.size() > 0) {
    Diff diff=new Diff(requestPattern,nearMisses.get(0).getRequest());
    return VerificationException.forUnmatchedRequestPattern(diff);
  }
  return new VerificationException(requestPattern,find(allRequests()));
}
