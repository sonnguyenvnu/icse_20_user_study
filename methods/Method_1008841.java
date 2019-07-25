private static Element marshall(Object o){
  try {
    org.w3c.dom.Document w3cDoc=XmlUtils.marshaltoW3CDomDocument(o);
    return w3cDoc.getDocumentElement();
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
