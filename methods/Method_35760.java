public static Document read(String xml){
  try {
    DocumentBuilderFactory dbf=newDocumentBuilderFactory();
    DocumentBuilder db=dbf.newDocumentBuilder();
    InputSource is=new InputSource(new StringReader(xml));
    return db.parse(is);
  }
 catch (  SAXException e) {
    throw XmlException.fromSaxException(e);
  }
catch (  Exception e) {
    return throwUnchecked(e,Document.class);
  }
}
