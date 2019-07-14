@SuppressWarnings({"unchecked","rawtypes"}) private List<Matcher<? super Throwable>> castedMatchers(){
  return new ArrayList<Matcher<? super Throwable>>((List)matchers);
}
