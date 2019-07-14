@Override public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  this.collectionOfFutureIndexes=collectionOfFutureIndexes;
  boolean refresh=false;
  for (  ReferenceData reference : references) {
    String typeName=reference.typeName;
    boolean enabled;
    if (reference.name == null) {
      enabled=false;
      try {
        for (        Future<Indexes> futureIndexes : collectionOfFutureIndexes) {
          if (futureIndexes.isDone()) {
            Map<String,Collection> index=futureIndexes.get().getIndex("typeDeclarations");
            if ((index != null) && (index.get(typeName) != null)) {
              enabled=true;
              break;
            }
          }
        }
      }
 catch (      Exception e) {
        assert ExceptionUtil.printStackTrace(e);
      }
    }
 else {
      try {
        typeName=searchTypeHavingMember(typeName,reference.name,reference.descriptor,entry);
        if (typeName != null) {
          reference.typeName=typeName;
          enabled=true;
        }
 else {
          enabled=false;
        }
      }
 catch (      Error e) {
        assert ExceptionUtil.printStackTrace(e);
        enabled=false;
      }
    }
    if (reference.enabled != enabled) {
      reference.enabled=enabled;
      refresh=true;
    }
  }
  if (refresh) {
    textArea.repaint();
  }
}
