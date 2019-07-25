public static void apply(WordprocessingMLPackage otherPackage,Alterations alterations) throws Docx4JException, JAXBException {
  if (alterations.getContentTypes() != null) {
    log.info("replacing [Content_Types].xml");
    ContentTypeManager newCTM=new ContentTypeManager();
    newCTM.parseContentTypesFile(new ByteArrayInputStream(alterations.getContentTypes()));
    otherPackage.setContentTypeManager(newCTM);
  }
  if (alterations.isEmpty())   return;
  List<PartName> removedParts=new ArrayList<PartName>();
  for (  Alteration a : alterations.getPartsDeleted()) {
    org.docx4j.xmlPackage.Part xmlpart=a.getPart();
    if (removedParts.contains(xmlpart.getName()))     continue;
    Part parentPart=otherPackage.getParts().get(a.getSourcePartName());
    if (a.getPart().getContentType().equals(ContentTypes.RELATIONSHIPS_PART)) {
      parentPart.setRelationships(null);
    }
 else {
      removedParts.addAll(parentPart.getRelationshipsPart().removePart(new PartName(xmlpart.getName())));
    }
  }
  for (  Alteration a : alterations.getPartsModified()) {
    log.info("Applying modifications to part: " + a.getPart().getName());
    if (a.getPart().getContentType().equals(ContentTypes.RELATIONSHIPS_PART)) {
      RelationshipsPart newRP=null;
      if (a.getSourcePartName().getName().equals("/")) {
        newRP=otherPackage.getRelationshipsPart(true);
      }
 else {
        Part parentPart=otherPackage.getParts().get(a.getSourcePartName());
        newRP=parentPart.getRelationshipsPart(true);
      }
      FlatOpcXmlImporter.populateRelationshipsPart(newRP,a.getPart().getXmlData().getAny());
    }
 else {
      Part targetPart=otherPackage.getParts().get(new PartName(a.getPart().getName()));
      if (targetPart == null) {
        log.error("Couldn't find " + a.getPart().getName() + " @ " + a.getSourcePartName().getName());
        continue;
      }
      Part tmpPart=FlatOpcXmlImporter.getRawPart(otherPackage.getContentTypeManager(),a.getPart(),null);
      if (targetPart instanceof JaxbXmlPart) {
        ((JaxbXmlPart)targetPart).setJaxbElement(((JaxbXmlPart)tmpPart).getJaxbElement());
      }
 else       if (targetPart instanceof XmlPart) {
        ((XmlPart)targetPart).setDocument(((XmlPart)tmpPart).getDocument());
      }
 else       if (targetPart instanceof CustomXmlDataStoragePart) {
        ((CustomXmlDataStoragePart)targetPart).setData(((CustomXmlDataStoragePart)tmpPart).getData());
      }
 else       if (targetPart instanceof BinaryPart) {
        ((BinaryPart)targetPart).setBinaryData(((BinaryPart)tmpPart).getBuffer());
      }
 else {
        log.error("TODO: handle " + targetPart.getClass().getName());
      }
    }
  }
  for (  Alteration a : alterations.getPartsAdded()) {
    log.info("Adding part: " + a.getPart().getName());
    if (a.getPart().getContentType().equals(ContentTypes.RELATIONSHIPS_PART)) {
      RelationshipsPart newRP=null;
      if (a.getSourcePartName().getName().equals("/")) {
        newRP=otherPackage.getRelationshipsPart(true);
      }
 else {
        Part parentPart=otherPackage.getParts().get(a.getSourcePartName());
        newRP=parentPart.getRelationshipsPart(true);
      }
      FlatOpcXmlImporter.populateRelationshipsPart(newRP,a.getPart().getXmlData().getAny());
    }
 else {
      Part parentPart=otherPackage.getParts().get(a.getSourcePartName());
      Part newPart=FlatOpcXmlImporter.getRawPart(otherPackage.getContentTypeManager(),a.getPart(),null);
      newPart.setOwningRelationshipPart(parentPart.getRelationshipsPart());
      newPart.getSourceRelationships().add(parentPart.getRelationshipsPart().getRel(new PartName(a.getPart().getName())));
      otherPackage.getParts().put(newPart);
      newPart.setPackage(otherPackage);
    }
  }
}
