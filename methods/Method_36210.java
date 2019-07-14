private Predicate<Request> matchedBy(final RequestPattern requestPattern){
  return new Predicate<Request>(){
    public boolean apply(    Request input){
      return requestPattern.isMatchedBy(input,Collections.<String,RequestMatcherExtension>emptyMap());
    }
  }
;
}
