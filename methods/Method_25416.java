@SafeVarargs public static Matcher<Tree> hasAnyAnnotation(Class<? extends Annotation>... annotations){
  ArrayList<Matcher<Tree>> matchers=new ArrayList<>(annotations.length);
  for (  Class<? extends Annotation> annotation : annotations) {
    matchers.add(hasAnnotation(annotation));
  }
  return anyOf(matchers);
}
