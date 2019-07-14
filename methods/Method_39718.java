@Override public void visitBaseType(final char descriptor){
  if (isTopLevelType()) {
    declarationTypeOffset=declaration.length();
  }
  super.visitBaseType(descriptor);
}
