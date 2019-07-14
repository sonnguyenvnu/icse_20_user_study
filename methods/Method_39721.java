@Override protected void endType(){
  super.endType();
  String type=declaration.subSequence(declarationTypeOffset,declaration.length()).toString();
  maybeUseType(type);
}
