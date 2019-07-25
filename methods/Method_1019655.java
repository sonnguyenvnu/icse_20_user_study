@Override protected void visit(Document document,String entryName,FieldsMetadata fieldsMetadata,IDocumentFormatter formatter,Map<String,Object> sharedContext) throws XDocReportException {
  try {
    Element rFontsElt=null;
    NodeList rFontsNodeList=XPathUtils.evaluateNodeSet(document,"//w:rFonts",DocxNamespaceContext.INSTANCE);
    for (int i=0; i < rFontsNodeList.getLength(); i++) {
      rFontsElt=(Element)rFontsNodeList.item(i);
      if (i == 0) {
        String set=formatter.getSetDirective(FONT_SIZE_TWO_KEY,FONT_SIZE_KEY_MULT_BY_2);
        StringBuilder setWithIf=new StringBuilder();
        setWithIf.append(formatter.getStartIfDirective(FONT_SIZE_KEY));
        setWithIf.append(set);
        setWithIf.append(formatter.getEndIfDirective(FONT_SIZE_KEY));
        Node firstChild=rFontsElt.getOwnerDocument().getDocumentElement().getFirstChild();
        Text newChild=rFontsElt.getOwnerDocument().createTextNode(setWithIf.toString());
        rFontsElt.getOwnerDocument().getDocumentElement().insertBefore(newChild,firstChild);
      }
      updateDynamicAttr(rFontsElt,"w:ascii",FONT_NAME_KEY,formatter);
      updateDynamicAttr(rFontsElt,"w:cs",FONT_NAME_KEY,formatter);
      updateDynamicAttr(rFontsElt,"w:hAnsi",FONT_NAME_KEY,formatter);
      Element szCsElt=DOMUtils.getFirstChildElementByTagName(rFontsElt.getParentNode(),"w:szCs");
      if (szCsElt != null) {
        updateDynamicAttr(szCsElt,"w:val",FONT_SIZE_KEY,formatter);
      }
      Element szElt=DOMUtils.getFirstChildElementByTagName(rFontsElt.getParentNode(),"w:sz");
      if (szElt != null) {
        if (szCsElt != null) {
          updateDynamicAttr(szElt,"w:val",FONT_SIZE_TWO_KEY,formatter);
        }
 else {
          updateDynamicAttr(szElt,"w:val",FONT_SIZE_KEY,formatter);
        }
      }
    }
  }
 catch (  XPathExpressionException e) {
    throw new XDocReportException(e);
  }
}
