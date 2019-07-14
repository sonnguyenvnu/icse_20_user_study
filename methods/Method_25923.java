private static boolean isPackagePrivateAndInDiffPackage(VarSymbol parentVariable,ClassTree currClass){
  if (!parentVariable.getModifiers().contains(Modifier.PRIVATE) && !parentVariable.getModifiers().contains(Modifier.PROTECTED) && !parentVariable.getModifiers().contains(Modifier.PUBLIC)) {
    if (!parentVariable.packge().equals(ASTHelpers.getSymbol(currClass).packge())) {
      return true;
    }
  }
  return false;
}
