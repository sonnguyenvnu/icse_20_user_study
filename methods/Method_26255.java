private Description checkRestriction(@Nullable RestrictedApi restriction,Tree where,VisitorState state){
  if (restriction == null) {
    return Description.NO_MATCH;
  }
  if (!restriction.allowedOnPath().isEmpty()) {
    JCCompilationUnit compilationUnit=(JCCompilationUnit)state.getPath().getCompilationUnit();
    String path=ASTHelpers.getFileName(compilationUnit);
    if (Pattern.matches(restriction.allowedOnPath(),path)) {
      return Description.NO_MATCH;
    }
  }
  boolean warn=Matchers.enclosingNode(shouldAllowWithWarning(restriction)).matches(where,state);
  boolean allow=Matchers.enclosingNode(shouldAllow(restriction)).matches(where,state);
  if (warn && allow) {
    return buildDescription(where).setMessage("The Restricted API ([" + restriction.checkerName() + "]" + restriction.explanation() + ") call here is both whitelisted-as-warning and " + "silently whitelisted. " + "Please remove one of the conflicting suppression annotations.").build();
  }
  if (allow) {
    return Description.NO_MATCH;
  }
  SeverityLevel level=warn ? SeverityLevel.WARNING : SeverityLevel.ERROR;
  Description.Builder description=Description.builder(where,restriction.checkerName(),restriction.link(),level,restriction.explanation());
  return description.build();
}
