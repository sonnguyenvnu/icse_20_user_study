@Override @SuppressWarnings("unchecked") public void index(API api,Container.Entry entry,Indexes indexes){
  javaModuleDeclarationSet.clear();
  javaModuleReferenceSet.clear();
  typeReferenceSet.clear();
  try (InputStream inputStream=entry.getInputStream()){
    ClassReader classReader=new ClassReader(inputStream);
    classReader.accept(classIndexer,SKIP_CODE | SKIP_DEBUG | SKIP_FRAMES);
    addToIndexes(indexes,"javaModuleDeclarations",javaModuleDeclarationSet,entry);
    addToIndexes(indexes,"javaModuleReferences",javaModuleReferenceSet,entry);
    addToIndexes(indexes,"typeReferences",typeReferenceSet,entry);
  }
 catch (  Exception e) {
    assert ExceptionUtil.printStackTrace(e);
  }
}
