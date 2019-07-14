@Override public boolean applyTo(NameDeclaration nameDeclaration){
  if (occurrence.getLocation() instanceof ASTMethodReference) {
    return false;
  }
  if (isDeclaredBefore(nameDeclaration) && isSameName(nameDeclaration)) {
    decl=nameDeclaration;
    return false;
  }
  return true;
}
