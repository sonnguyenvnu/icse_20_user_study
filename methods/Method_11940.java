@SuppressWarnings("unchecked") private Matcher<Throwable> cast(Matcher<?> singleMatcher){
  return (Matcher<Throwable>)singleMatcher;
}
