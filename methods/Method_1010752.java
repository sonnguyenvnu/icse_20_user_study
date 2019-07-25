protected void init(){
  assert getNode() != null && getConcept() != null;
  for (  SProperty sProperty : getConcept().getProperties()) {
    if (!sProperty.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
      addProperty(sProperty);
    }
  }
  for (  SReferenceLink sReferenceLink : getConcept().getReferenceLinks()) {
    if (!sReferenceLink.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
      addReferenceLink(sReferenceLink);
    }
  }
  for (  SContainmentLink sContainmentLink : getConcept().getContainmentLinks()) {
    if (!sContainmentLink.getOwner().equals(SNodeUtil.concept_BaseConcept)) {
      addContainmentLink(sContainmentLink);
    }
  }
  super.init();
}
