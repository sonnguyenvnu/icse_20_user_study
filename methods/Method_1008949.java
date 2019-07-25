private OpcPackage process(HashMap<String,ByteArray> partByteArrays) throws Docx4JException {
  long startTime=System.currentTimeMillis();
  ContentTypeManager ctm=new ContentTypeManager();
  try {
    InputStream is=getInputStreamFromZippedPart(partByteArrays,"[Content_Types].xml");
    ctm.parseContentTypesFile(is);
  }
 catch (  IOException e) {
    throw new Docx4JException("Couldn't get [Content_Types].xml from ZipFile",e);
  }
catch (  NullPointerException e) {
    throw new Docx4JException("Couldn't get [Content_Types].xml from ZipFile",e);
  }
  RelationshipsPart rp=RelationshipsPart.createPackageRels();
  populatePackageRels(partByteArrays,rp);
  String mainPartName=PackageRelsUtil.getNameOfMainPart(rp);
  String pkgContentType=ctm.getContentType(new PartName("/" + mainPartName));
  OpcPackage p=ctm.createPackage(pkgContentType);
  log.info("Instantiated package of type " + p.getClass().getName());
  p.setRelationships(rp);
  rp.setSourceP(p);
  addPartsFromRelationships(partByteArrays,p,rp,ctm);
  registerCustomXmlDataStorageParts(p);
  long endTime=System.currentTimeMillis();
  log.info("package read;  elapsed time: " + Math.round((endTime - startTime)) + " ms");
  return p;
}
