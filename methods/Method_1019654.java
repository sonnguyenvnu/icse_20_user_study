@Override protected void visit(Document document,String entryName,FieldsMetadata fieldsMetadata,IDocumentFormatter formatter,Map<String,Object> sharedContext) throws XDocReportException {
  try {
    Element fldSimpleElt=null;
    NodeList fldSimpleNodeList=XPathUtils.evaluateNodeSet(document.getDocumentElement(),"//w:fldSimple",DocxNamespaceContext.INSTANCE);
    for (int i=0; i < fldSimpleNodeList.getLength(); i++) {
      fldSimpleElt=(Element)fldSimpleNodeList.item(i);
      processFldSimple(fldSimpleElt,fieldsMetadata,formatter,sharedContext);
    }
  }
 catch (  XPathExpressionException e) {
    throw new XDocReportException(e);
  }
}
