private static Matcher<Tree> shouldAllowWithWarning(RestrictedApi api){
  try {
    return Matchers.hasAnyAnnotation(api.whitelistWithWarningAnnotations());
  }
 catch (  MirroredTypesException e) {
    return Matchers.hasAnyAnnotation(e.getTypeMirrors());
  }
}
