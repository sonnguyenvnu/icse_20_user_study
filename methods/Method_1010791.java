@Override protected void init(){
  super.init();
  for (  SProperty sProperty : getNode().getProperties()) {
    if (sProperty.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
      addProperty(sProperty);
    }
  }
  for (  SReference sReference : getNode().getReferences()) {
    SReferenceLink link=sReference.getLink();
    assert link != null : "Null meta-link from node: " + getNode() + ", role: " + sReference.getRole();
    if (link.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
      addReferenceLink(link);
    }
  }
  for (  SNode child : getNode().getChildren()) {
    SContainmentLink containmentLink=child.getContainmentLink();
    assert containmentLink != null : "Null meta-containmentLink returned for the child of node: " + getNode() + ", child: " + child;
    if (containmentLink.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
      addContainmentLink(containmentLink);
    }
  }
}
