@Factory public static <T extends Throwable>Matcher<T> hasMessage(final Matcher<String> matcher){
  return new ThrowableMessageMatcher<T>(matcher);
}
