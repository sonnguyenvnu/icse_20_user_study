public DocumentFragment message(AbstractConversionContext context,String message){
  if (!context.getLog().isDebugEnabled())   return null;
  String documentFragment=getOutputPrefix() + message + getOutputSuffix();
  context.getLog().debug(documentFragment);
  StringReader reader=new StringReader(documentFragment);
  InputSource inputSource=new InputSource(reader);
  Document doc=null;
  try {
    doc=XmlUtils.getNewDocumentBuilder().parse(inputSource);
  }
 catch (  Exception e) {
    context.getLog().error(e.getMessage(),e);
  }
  reader.close();
  DocumentFragment docfrag=doc.createDocumentFragment();
  docfrag.appendChild(doc.getDocumentElement());
  return docfrag;
}
