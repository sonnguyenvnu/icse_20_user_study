private MPSPsiNode convert(SNode node){
  MPSPsiNode psiNode=MPSPsiProvider.getInstance(getProject()).create(node.getNodeId(),node.getConcept(),node.getContainmentLink() == null ? null : node.getContainmentLink().getName());
  myNodes.put(node.getNodeId(),psiNode);
  for (  SProperty property : node.getProperties()) {
    psiNode.setProperty(property.getName(),node.getProperty(property));
  }
  for (  SReference ref : node.getReferences()) {
    SAbstractLink link=ref.getLink();
    SAbstractConcept linkTargetConcept=null;
    if (link != null) {
      linkTargetConcept=link.getTargetConcept();
    }
    MPSPsiRef psiRef=null;
    if (ref instanceof StaticReference) {
      psiRef=MPSPsiProvider.getInstance(getProject()).createReferenceNode(ref.getLink(),linkTargetConcept,ref.getTargetSModelReference(),ref.getTargetNodeId());
    }
 else     if (ref instanceof DynamicReference) {
      psiRef=MPSPsiProvider.getInstance(getProject()).createReferenceNode(ref.getLink(),linkTargetConcept,((DynamicReference)ref).getResolveInfo());
    }
    if (psiRef != null) {
      psiNode.addChild(null,psiRef);
    }
  }
  for (  SNode root : node.getChildren()) {
    MPSPsiNode psiChild=convert(root);
    MPSPsiNodeBase artificialParent=psiNode.getParentFor(psiChild);
    MPSPsiNodeBase wouldBeParent=artificialParent == null ? psiNode : artificialParent;
    wouldBeParent.addChildLast(psiChild);
  }
  return psiNode;
}
