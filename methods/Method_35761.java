public static String render(Node node){
  try {
    StringWriter sw=new StringWriter();
    Transformer transformer=TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OMIT_XML_DECLARATION,"yes");
    transformer.setOutputProperty(INDENT,"yes");
    transformer.transform(new DOMSource(node),new StreamResult(sw));
    return sw.toString();
  }
 catch (  TransformerException e) {
    return throwUnchecked(e,String.class);
  }
}
