private void update(SNode method,SNode baseMethod){
  if (myRemoveAttributes) {
    for (    SNode child : SNodeOperations.getChildren(method)) {
      removeAttributes(child);
    }
  }
  boolean isAbstractMethod=((boolean)(Boolean)BHReflection.invoke0(baseMethod,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,"jetbrains.mps.baseLanguage.structure.BaseMethodDeclaration"),SMethodTrimmedId.create("isAnAbstractMethod",null,"28P2dHxCoRl")));
  SNode defaultExpr;
  if (isAbstractMethod) {
    defaultExpr=((SNode)BHReflection.invoke0(SLinkOperations.getTarget(baseMethod,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1fdL,"returnType")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506dL,"jetbrains.mps.baseLanguage.structure.Type"),SMethodTrimmedId.create("createDefaultTypeExpression",null,"2UvJdVpqUA4")));
  }
 else {
    SNode sourceMethodConcept=SLinkOperations.getTarget(SNodeOperations.getNodeAncestor(baseMethod,MetaAdapterFactory.getConcept(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d43447b1aL,"jetbrains.mps.lang.behavior.structure.ConceptBehavior"),false,false),MetaAdapterFactory.getReferenceLink(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d43447b1aL,0x11d43447b1fL,"concept"));
    if (SNodeOperations.isInstanceOf(sourceMethodConcept,MetaAdapterFactory.getConcept(0xc72da2b97cce4447L,0x8389f407dc1158b7L,0xf979ba0450L,"jetbrains.mps.lang.structure.structure.ConceptDeclaration"))) {
      sourceMethodConcept=null;
    }
    Iterable<SNode> paramList=ListSequence.fromList(SLinkOperations.getChildren(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1feL,"parameter"))).select(new ISelector<SNode,SNode>(){
      public SNode select(      SNode it){
        return _quotation_createNode_7wts1u_a0a0a0a2a0e0j(it);
      }
    }
);
    defaultExpr=_quotation_createNode_7wts1u_a0d0a4a9(sourceMethodConcept,((SNode)BHReflection.invoke0(baseMethod,MetaAdapterFactory.getConcept(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d4348057eL,"jetbrains.mps.lang.behavior.structure.ConceptMethodDeclaration"),SMethodTrimmedId.create("getOverridenMethod",MetaAdapterFactory.getConcept(0xaf65afd8f0dd4942L,0x87d963a55f2a9db1L,0x11d4348057eL,"jetbrains.mps.lang.behavior.structure.ConceptMethodDeclaration"),"hP3pnNO"))),Sequence.fromIterable(paramList).toListSequence());
  }
  if (SNodeOperations.isInstanceOf(SLinkOperations.getTarget(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1fdL,"returnType")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc6bf96dL,"jetbrains.mps.baseLanguage.structure.VoidType"))) {
    if (!(isAbstractMethod)) {
      ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1ffL,"body")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).addElement(_quotation_createNode_7wts1u_a0a0a0a6a9(defaultExpr));
    }
  }
 else {
    ListSequence.fromList(SLinkOperations.getChildren(SLinkOperations.getTarget(method,MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b1fcL,0xf8cc56b1ffL,"body")),MetaAdapterFactory.getContainmentLink(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8cc56b200L,0xf8cc6bf961L,"statement"))).addElement(getReturnStatement(defaultExpr));
  }
}
