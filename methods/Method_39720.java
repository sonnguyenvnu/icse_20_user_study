@Override protected void startType(){
  super.startType();
  if (isTopLevelType()) {
    declarationTypeOffset=declaration.length();
  }
}
