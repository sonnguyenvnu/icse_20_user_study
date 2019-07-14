private Predicate<Session> isForRequest(final HttpRequest dumpedRequest){
  return new Predicate<Session>(){
    @Override public boolean apply(    final Session session){
      HttpRequest request=session.getRequest();
      return new HttpRequestFailoverMatcher(request).match(dumpedRequest);
    }
  }
;
}
