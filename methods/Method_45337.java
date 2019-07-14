private Predicate<Request> matched(){
  return new Predicate<Request>(){
    @Override public boolean apply(    final Request request){
      return matcher.match(request);
    }
  }
;
}
