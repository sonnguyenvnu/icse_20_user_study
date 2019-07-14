public static Matcher<MethodTree> methodHasVisibility(Visibility visibility){
  return new MethodVisibility(visibility);
}
