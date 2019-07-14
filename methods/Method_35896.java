private boolean anyValueMatches(final StringValuePattern valuePattern){
  return any(values,new Predicate<String>(){
    public boolean apply(    String headerValue){
      return valuePattern.match(headerValue).isExactMatch();
    }
  }
);
}
