private Matcher<Throwable> allOfTheMatchers(){
  if (matchers.size() == 1) {
    return cast(matchers.get(0));
  }
  return allOf(castedMatchers());
}
