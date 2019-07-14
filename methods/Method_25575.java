private static boolean isVisible(VarSymbol var,final TreePath path){
switch (var.getKind()) {
case ENUM_CONSTANT:
case FIELD:
    List<ClassSymbol> enclosingClasses=StreamSupport.stream(path.spliterator(),false).filter(tree -> tree instanceof ClassTree).map(ClassTree.class::cast).map(ASTHelpers::getSymbol).collect(Collectors.toCollection(ArrayList::new));
  if (!var.isStatic()) {
    if (inStaticContext(path)) {
      return false;
    }
    if (lowerThan(path,(curr,unused) -> curr instanceof ClassTree && ASTHelpers.getSymbol((ClassTree)curr).isStatic(),(curr,unused) -> curr instanceof ClassTree && ASTHelpers.getSymbol((ClassTree)curr).equals(var.owner))) {
      return false;
    }
  }
if (enclosingClasses.contains(ASTHelpers.enclosingClass(var))) {
  return true;
}
PackageSymbol enclosingPackage=((JCCompilationUnit)path.getCompilationUnit()).packge;
Set<Modifier> modifiers=var.getModifiers();
if (Objects.equals(enclosingPackage,ASTHelpers.enclosingPackage(var))) {
return !modifiers.contains(Modifier.PRIVATE);
}
return modifiers.contains(Modifier.PUBLIC) || modifiers.contains(Modifier.PROTECTED);
case PARAMETER:
case LOCAL_VARIABLE:
if (lowerThan(path,(curr,parent) -> curr.getKind() == Kind.LAMBDA_EXPRESSION || (curr.getKind() == Kind.NEW_CLASS && ((NewClassTree)curr).getClassBody() != null) || (curr.getKind() == Kind.CLASS && parent.getKind() == Kind.BLOCK),(curr,unused) -> Objects.equals(var.owner,ASTHelpers.getSymbol(curr)))) {
if ((var.flags() & (Flags.FINAL | Flags.EFFECTIVELY_FINAL)) == 0) {
return false;
}
}
return true;
case EXCEPTION_PARAMETER:
case RESOURCE_VARIABLE:
return true;
default :
throw new IllegalArgumentException("Unexpected variable type: " + var.getKind());
}
}
