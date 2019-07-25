/** 
 * Rename this part; updates source rels and parts collection.   Useful when merging documents, if you need to  take action to avoid name collisions.
 * @param newName
 * @since 3.2.0
 */
public void rename(PartName newName){
  log.info("Renaming part " + this.getPartName().getName() + " to " + newName.getName());
  this.getPackage().getParts().remove(this.getPartName());
  for (  Relationship rel : sourceRelationships) {
    URI tobeRelativized=newName.getURI();
    Relationships thisRels=(Relationships)rel.getParent();
    RelationshipsPart thisRelsPart=(RelationshipsPart)thisRels.getParent();
    if (thisRelsPart == null) {
      log.error("Couldn't determine rels part from rels object");
    }
    URI relativizeAgainst=thisRelsPart.getSourceURI();
    log.debug("Relativising target " + tobeRelativized + " against source " + relativizeAgainst);
    String result=org.docx4j.openpackaging.URIHelper.relativizeURI(relativizeAgainst,tobeRelativized).toString();
    if (relativizeAgainst.getPath().equals("/") && result.startsWith("/")) {
      result=result.substring(1);
    }
    log.debug("Result " + result);
    rel.setTarget(result);
  }
  this.setPartName(newName);
  this.getPackage().getParts().put(this);
}
