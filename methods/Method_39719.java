@Override public void visitClassType(final String name){
  if (isTopLevelType()) {
    declarationTypeOffset=declaration.length();
  }
  super.visitClassType(name);
}
