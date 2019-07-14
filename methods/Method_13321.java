@Override public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  this.collectionOfFutureIndexes=collectionOfFutureIndexes;
  boolean refresh=false;
  for (  ReferenceData reference : references) {
    ModuleInfoReferenceData moduleInfoReferenceData=(ModuleInfoReferenceData)reference;
    boolean enabled=false;
    try {
      for (      Future<Indexes> futureIndexes : collectionOfFutureIndexes) {
        if (futureIndexes.isDone()) {
          Map<String,Collection> index;
          String key;
switch (moduleInfoReferenceData.type) {
case TYPE:
            index=futureIndexes.get().getIndex("typeDeclarations");
          key=reference.typeName;
        break;
case PACKAGE:
      index=futureIndexes.get().getIndex("packageDeclarations");
    key=reference.typeName;
  break;
default :
index=futureIndexes.get().getIndex("javaModuleDeclarations");
key=reference.name;
break;
}
if ((index != null) && (index.get(key) != null)) {
enabled=true;
break;
}
}
}
}
 catch (Exception e) {
assert ExceptionUtil.printStackTrace(e);
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
