public org.docx4j.xmlPackage.Package get() throws Docx4JException {
  try {
    pkgResult=factory.createPackage();
    RelationshipsPart rp=packageIn.getRelationshipsPart();
    saveRawXmlPart(rp);
    addPartsFromRelationships(rp);
  }
 catch (  Exception e) {
    e.printStackTrace();
    if (e instanceof Docx4JException) {
      throw (Docx4JException)e;
    }
 else {
      throw new Docx4JException("Failed to save package",e);
    }
  }
  log.debug("...Done!");
  return pkgResult;
}
