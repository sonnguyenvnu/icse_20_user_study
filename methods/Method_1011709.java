void update(SNode method,SNode baseMethod){
  if (SModelStereotype.isStubModel(SNodeOperations.getModel(baseMethod))) {
    final SNode startNode=(ListSequence.fromList(SLinkOperations.getChildren(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1feL,"parameter"))).all(new IWhereFilter<SNode>(){
      public boolean accept(      SNode it){
        return SPropertyOperations.getString(it,MetaAdapterFactory.getProperty(0xceab519525ea4f22L,0x9b92103b95ca8c0cL,0x110396eaaa4L,0x110396ec041L,"name")).matches("p[0-9]+");
      }
    }
) ? method : SLinkOperations.getTarget(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1ffL,"body")));
    setVariableNames(startNode,MapSequence.fromMap(new HashMap<String,Integer>()));
  }
  if (myRemoveAttributes) {
    for (    SNode child : SNodeOperations.getChildren(method)) {
      removeAttributes(child);
    }
  }
  if (myInsertOverride && SNodeOperations.isInstanceOf(baseMethod,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b21dL,"jetbrains.mps.baseLanguage.structure.InstanceMethodDeclaration"))) {
    boolean isNeedAddAnnotation=true;
    for (    SNode annotation : SLinkOperations.getChildren(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x114a6be947aL,0x114a6beb0bdL,"annotation"))) {
      if (SLinkOperations.hasPointer(annotation,MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x114a6b4ccabL,0x114a6b85d40L,"annotation"),new SNodePointer("6354ebe7-c22a-4a0f-ac54-50b52ab9b065/java:java.lang(JDK/)","~Override"))) {
        isNeedAddAnnotation=false;
        break;
      }
    }
    if (isNeedAddAnnotation) {
      ListSequence.fromList(SLinkOperations.getChildren(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x114a6be947aL,0x114a6beb0bdL,"annotation"))).addElement(_quotation_createNode_tfz3o4_a0a0a2a2a11());
    }
  }
  Iterable<SNode> paramList=ListSequence.fromList(SLinkOperations.getChildren(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1feL,"parameter"))).select(new ISelector<SNode,SNode>(){
    public SNode select(    SNode it){
      return _quotation_createNode_tfz3o4_a0a0a0a4a11(it);
    }
  }
);
  if (SNodeOperations.isInstanceOf(baseMethod,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b21dL,"jetbrains.mps.baseLanguage.structure.InstanceMethodDeclaration"))) {
    boolean isAbstractMethod=((boolean)(Boolean)BHReflection.invoke0(SNodeOperations.cast(baseMethod,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b21dL,"jetbrains.mps.baseLanguage.structure.InstanceMethodDeclaration")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration"),SMethodTrimmedId.create("isAnAbstractMethod",null,"28P2dHxCoRl")));
    SNode defaultExpr=null;
    if (isAbstractMethod) {
      defaultExpr=((SNode)BHReflection.invoke0(SLinkOperations.getTarget(baseMethod,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1fdL,"returnType")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506dL,"jetbrains.mps.baseLanguage.structure.Type"),SMethodTrimmedId.create("createDefaultTypeExpression",null,"2UvJdVpqUA4")));
    }
 else {
      if (SNodeOperations.isInstanceOf(SNodeOperations.getParent(baseMethod),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101edd46144L,"jetbrains.mps.baseLanguage.structure.Interface"))) {
        SNode curClassifier=SNodeOperations.cast(SNodeOperations.getParent(method),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier"));
        final SNode baseInterface=SNodeOperations.cast(SNodeOperations.getParent(baseMethod),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101edd46144L,"jetbrains.mps.baseLanguage.structure.Interface"));
        List<SNode> directAncestors=((List<SNode>)BHReflection.invoke0(curClassifier,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier"),SMethodTrimmedId.create("getExtendedClassifierTypes",null,"1UeCwxlWKny")));
        SNode directParentWhichExtendsBase=ListSequence.fromList(directAncestors).findFirst(new IWhereFilter<SNode>(){
          public boolean accept(          SNode it){
            return ListSequence.fromList(((List<SNode>)BHReflection.invoke0(SNodeOperations.cast(((SNode)BHReflection.invoke0(it,MetaAdapterFactory.getInterfaceConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x66c71d82c2eb7f7eL,"jetbrains.mps.baseLanguage.structure.IClassifierType"),SMethodTrimmedId.create("getClassifier",null,"6r77ob2URY9"))),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier"),SMethodTrimmedId.create("getAllSuperClassifiers",MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101d9d3ca30L,"jetbrains.mps.baseLanguage.structure.Classifier"),"59G_UM6ah0X")))).contains(baseInterface);
          }
        }
);
        if (directParentWhichExtendsBase != null && SNodeOperations.isInstanceOf(((SNode)BHReflection.invoke0(directParentWhichExtendsBase,MetaAdapterFactory.getInterfaceConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x66c71d82c2eb7f7eL,"jetbrains.mps.baseLanguage.structure.IClassifierType"),SMethodTrimmedId.create("getClassifier",null,"6r77ob2URY9"))),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x101edd46144L,"jetbrains.mps.baseLanguage.structure.Interface"))) {
          defaultExpr=_quotation_createNode_tfz3o4_a0a0e0a0a2a5a11(Sequence.fromIterable(paramList).toListSequence(),((SNode)BHReflection.invoke0(directParentWhichExtendsBase,MetaAdapterFactory.getInterfaceConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x66c71d82c2eb7f7eL,"jetbrains.mps.baseLanguage.structure.IClassifierType"),SMethodTrimmedId.create("getClassifier",null,"6r77ob2URY9"))),baseMethod);
        }
      }
      if (defaultExpr == null) {
        defaultExpr=_quotation_createNode_tfz3o4_a0a0b0a2a5a11(baseMethod,Sequence.fromIterable(paramList).toListSequence());
      }
    }
    if (SNodeOperations.isInstanceOf(SLinkOperations.getTarget(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1fdL,"returnType")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc6bf96dL,"jetbrains.mps.baseLanguage.structure.VoidType"))) {
      if (!(isAbstractMethod)) {
        ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1ffL,"body")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).addElement(_quotation_createNode_tfz3o4_a0a0a0a3a5a11(defaultExpr));
      }
    }
 else {
      ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1ffL,"body")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).addElement(getReturnStatement(defaultExpr));
    }
  }
 else {
    if (SNodeOperations.isInstanceOf(baseMethod,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b204L,"jetbrains.mps.baseLanguage.structure.ConstructorDeclaration"))) {
      SNode superConstructor=_quotation_createNode_tfz3o4_a0a0a0a5a11(Sequence.fromIterable(paramList).toListSequence());
      SLinkOperations.setTarget(superConstructor,MetaAdapterFactory.getReferenceLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0x11857355952L,0xf8c78301adL,"baseMethodDeclaration"),SNodeOperations.cast(baseMethod,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b204L,"jetbrains.mps.baseLanguage.structure.ConstructorDeclaration")));
      ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1ffL,"body")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).addElement(superConstructor);
    }
  }
}
