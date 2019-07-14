void generateKeyAndSet(SectionContext c,String globalKey){
  final Section parentScope=c.getSectionScope();
  final String uniqueGlobalKey=parentScope == null ? globalKey : parentScope.generateUniqueGlobalKeyForChild(this,globalKey);
  setGlobalKey(uniqueGlobalKey);
  c.getKeyHandler().registerKey(uniqueGlobalKey);
}
