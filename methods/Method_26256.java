private static Matcher<Tree> shouldAllow(RestrictedApi api){
  try {
    return Matchers.hasAnyAnnotation(api.whitelistAnnotations());
  }
 catch (  MirroredTypesException e) {
    return Matchers.hasAnyAnnotation(e.getTypeMirrors());
  }
}
