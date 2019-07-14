private static boolean canBecomeStaticMember(VarSymbol sym){
  ClassSymbol owningClass=sym.enclClass();
switch (owningClass.getNestingKind()) {
case LOCAL:
case ANONYMOUS:
    return false;
default :
  return !owningClass.isInner();
}
}
