public void write(RuleSet ruleSet){
  try {
    DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
    document=documentBuilder.newDocument();
    ruleSetFileNames=new HashSet<>();
    Element ruleSetElement=createRuleSetElement(ruleSet);
    document.appendChild(ruleSetElement);
    TransformerFactory transformerFactory=TransformerFactory.newInstance();
    try {
      transformerFactory.setAttribute("indent-number",3);
    }
 catch (    IllegalArgumentException iae) {
      LOG.log(Level.FINE,"Couldn't set indentation",iae);
    }
    Transformer transformer=transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.METHOD,"xml");
    transformer.setOutputProperty(OutputKeys.INDENT,"yes");
    transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
    transformer.transform(new DOMSource(document),new StreamResult(outputStream));
  }
 catch (  DOMException e) {
    throw new RuntimeException(e);
  }
catch (  FactoryConfigurationError e) {
    throw new RuntimeException(e);
  }
catch (  ParserConfigurationException e) {
    throw new RuntimeException(e);
  }
catch (  TransformerException e) {
    throw new RuntimeException(e);
  }
}
