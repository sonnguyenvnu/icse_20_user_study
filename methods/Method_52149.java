private boolean isMissingComment(AccessNode decl){
  return decl.isPackagePrivate() && !interestingLineNumberComments.contains(decl.getBeginLine()) && !hasIgnoredAnnotation(decl);
}
