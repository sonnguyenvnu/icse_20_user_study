public static String prettyPrint(Document doc){
  try {
    TransformerFactory transformerFactory=createTransformerFactory();
    Transformer transformer=transformerFactory.newTransformer();
    transformer.setOutputProperty(INDENT,"yes");
    transformer.setOutputProperty(OMIT_XML_DECLARATION,"yes");
    StreamResult result=new StreamResult(new StringWriter());
    DOMSource source=new DOMSource(doc);
    transformer.transform(source,result);
    return result.getWriter().toString();
  }
 catch (  Exception e) {
    return throwUnchecked(e,String.class);
  }
}
