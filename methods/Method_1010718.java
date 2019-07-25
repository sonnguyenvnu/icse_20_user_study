protected void init(){
  assert mySNode != null && myConcept != null;
  for (  SProperty sProperty : mySNode.getProperties()) {
    if (!sProperty.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
      myProperties.add(sProperty);
    }
  }
  for (  SReference sReference : mySNode.getReferences()) {
    SReferenceLink link=sReference.getLink();
    assert link != null : "Null meta-link from node: " + this.mySNode + ", role: " + sReference.getRole();
    if (!link.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
      myReferenceLinks.add(link);
    }
  }
  for (  SNode child : this.mySNode.getChildren()) {
    SContainmentLink containmentLink=child.getContainmentLink();
    assert containmentLink != null : "Null meta-containmentLink returned for the child of node: " + this.mySNode + ", child: " + child;
    if (!containmentLink.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
      myContainmentLinks.add(containmentLink);
    }
  }
}
