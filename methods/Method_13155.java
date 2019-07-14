@Override @SuppressWarnings("unchecked") public void index(API api,Container.Entry entry,Indexes indexes){
  try (InputStream inputStream=entry.getInputStream()){
    Listener listener=new Listener(entry);
    ANTLRJavaParser.parse(new ANTLRInputStream(inputStream),listener);
    addToIndexes(indexes,"typeDeclarations",listener.getTypeDeclarationSet(),entry);
    addToIndexes(indexes,"constructorDeclarations",listener.getConstructorDeclarationSet(),entry);
    addToIndexes(indexes,"methodDeclarations",listener.getMethodDeclarationSet(),entry);
    addToIndexes(indexes,"fieldDeclarations",listener.getFieldDeclarationSet(),entry);
    addToIndexes(indexes,"typeReferences",listener.getTypeReferenceSet(),entry);
    addToIndexes(indexes,"constructorReferences",listener.getConstructorReferenceSet(),entry);
    addToIndexes(indexes,"methodReferences",listener.getMethodReferenceSet(),entry);
    addToIndexes(indexes,"fieldReferences",listener.getFieldReferenceSet(),entry);
    addToIndexes(indexes,"strings",listener.getStringSet(),entry);
    Map<String,Collection> index=indexes.getIndex("subTypeNames");
    for (    Map.Entry<String,HashSet<String>> e : listener.getSuperTypeNamesMap().entrySet()) {
      String typeName=e.getKey();
      for (      String superTypeName : e.getValue()) {
        index.get(superTypeName).add(typeName);
      }
    }
  }
 catch (  IOException e) {
    assert ExceptionUtil.printStackTrace(e);
  }
}
