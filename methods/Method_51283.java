@Override public boolean applyTo(NameDeclaration nameDeclaration){
  if (images.contains(nameDeclaration.getImage())) {
    decl=nameDeclaration;
    return false;
  }
  return true;
}
