/** 
 * Default build method, get's called with a P.Hyperlink. 
 */
public void build(AbstractWmlConversionContext conversionContext,Object node,Node content) throws TransformerException {
  Relationship relationship=null;
  RelationshipsPart rPart=null;
  P.Hyperlink pHyperlink=(P.Hyperlink)node;
  this.content=content;
  setAnchor(pHyperlink.getAnchor());
  setDocLocation(pHyperlink.getDocLocation());
  setRId(pHyperlink.getId());
  setTgtFrame(pHyperlink.getTgtFrame());
  setTooltip(pHyperlink.getTooltip());
  if (conversionContext.getCurrentPart() == null) {
    log.warn("set currentPart (via conversionContext)");
  }
 else   if ((getRId() != null) && (getRId().length() > 0)) {
    rPart=conversionContext.getCurrentPart().getRelationshipsPart();
    if (rPart == null) {
      log.error("RelationshipsPart is missing!");
    }
 else {
      log.debug("looking for rel" + getRId());
      relationship=rPart.getRelationshipByID(getRId());
      if ((relationship != null) && (Namespaces.HYPERLINK.equals(relationship.getType()))) {
        setTarget(relationship.getTarget());
        setExternal("External".equals(relationship.getTargetMode()));
      }
    }
  }
}
