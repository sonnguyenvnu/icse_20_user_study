public static Matcher<Tree> hasAnyAnnotation(List<? extends TypeMirror> mirrors){
  ArrayList<Matcher<Tree>> matchers=new ArrayList<>(mirrors.size());
  for (  TypeMirror mirror : mirrors) {
    matchers.add(hasAnnotation(mirror));
  }
  return anyOf(matchers);
}
