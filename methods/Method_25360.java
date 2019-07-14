@SuppressWarnings("unchecked") public static <T extends Tree>Matcher<T> hasProvidesAnnotation(){
  return (Matcher<T>)HAS_PROVIDES_ANNOTATION;
}
