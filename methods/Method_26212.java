@Override public Description matchCompilationUnit(CompilationUnitTree tree,VisitorState state){
  if (tree.getPackageName() == null) {
    return Description.NO_MATCH;
  }
  if (ASTHelpers.hasAnnotation(tree.getPackage(),SuppressPackageLocation.class,state)) {
    return Description.NO_MATCH;
  }
  PackageSymbol packageSymbol=((JCCompilationUnit)state.getPath().getCompilationUnit()).packge;
  if (packageSymbol == null) {
    return Description.NO_MATCH;
  }
  String packageName=packageSymbol.fullname.toString();
  String actualFileName=ASTHelpers.getFileName(tree);
  if (actualFileName == null) {
    return Description.NO_MATCH;
  }
  String actualPath=actualFileName.substring(0,actualFileName.lastIndexOf('/'));
  String expectedSuffix="/" + DOT_MATCHER.replaceFrom(packageName,'/');
  if (actualPath.endsWith(expectedSuffix)) {
    return Description.NO_MATCH;
  }
  String message=String.format("Expected package %s to be declared in a directory ending with %s, instead found %s",packageName,expectedSuffix,actualPath);
  return buildDescription(tree.getPackageName()).setMessage(message).build();
}
