public boolean navigate(MPSProject p,SNode node){
  assert SwingUtilities.isEventDispatchThread();
  assert p.getModelAccess().canWrite();
  boolean isClassifier=SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier"));
  boolean isConstructor=SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b204L,"jetbrains.mps.baseLanguage.structure.ConstructorDeclaration"));
  boolean isMethod=SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration")) && SNodeOperations.isInstanceOf(SNodeOperations.getParent(node),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier"));
  boolean isField=(SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c108ca68L,"jetbrains.mps.baseLanguage.structure.FieldDeclaration")) || SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf93c84351fL,"jetbrains.mps.baseLanguage.structure.StaticFieldDeclaration"))) && SNodeOperations.isInstanceOf(SNodeOperations.getParent(node),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier"));
  assert isClassifier || isConstructor || isMethod || isField;
  if (isClassifier) {
    final String fqName=((String)BHReflection.invoke0(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier")),MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,"jetbrains.mps.lang.core.structure.INamedConcept"),SMethodTrimmedId.create("getFqName",null,"hEwIO9y")));
    return open(new _FunctionTypes._void_P1_E1<IProjectHandler,RemoteException>(){
      public void invoke(      IProjectHandler h) throws RemoteException {
        h.openClass(fqName);
      }
    }
,p);
  }
 else {
    SNode classifier=SNodeOperations.cast(SNodeOperations.getParent(node),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier"));
    assert classifier != null;
    final String classifierName=((String)BHReflection.invoke0(classifier,MetaAdapterFactory.getInterfaceConcept(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,"jetbrains.mps.lang.core.structure.INamedConcept"),SMethodTrimmedId.create("getFqName",null,"hEwIO9y")));
    if (isMethod) {
      SNode method=SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration"));
      final String methodName=SPropertyOperations.getString(method,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name"));
      final int paramCount=ListSequence.fromList(SLinkOperations.getChildren(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1feL,"parameter"))).count();
      return open(new _FunctionTypes._void_P1_E1<IProjectHandler,RemoteException>(){
        public void invoke(        IProjectHandler h) throws RemoteException {
          h.openMethod(classifierName,methodName,paramCount);
        }
      }
,p);
    }
 else     if (isConstructor) {
      final int paramCount=ListSequence.fromList(SLinkOperations.getChildren(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b204L,"jetbrains.mps.baseLanguage.structure.ConstructorDeclaration")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1feL,"parameter"))).count();
      return open(new _FunctionTypes._void_P1_E1<IProjectHandler,RemoteException>(){
        public void invoke(        IProjectHandler h) throws RemoteException {
          h.openConstructor(classifierName,paramCount);
        }
      }
,p);
    }
 else {
      final String fieldName=node.getName();
      return open(new _FunctionTypes._void_P1_E1<IProjectHandler,RemoteException>(){
        public void invoke(        IProjectHandler h) throws RemoteException {
          h.openField(classifierName,fieldName);
        }
      }
,p);
    }
  }
}
