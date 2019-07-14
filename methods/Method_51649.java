private void saveSettings(){
  try {
    DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
    Document document=documentBuilder.newDocument();
    Element settingsElement=document.createElement("settings");
    document.appendChild(settingsElement);
    Element codeElement=document.createElement("code");
    settingsElement.appendChild(codeElement);
    codeElement.setAttribute("language-version",getLanguageVersion().getTerseName());
    codeElement.appendChild(document.createCDATASection(codeEditorPane.getText()));
    Element xpathElement=document.createElement("xpath");
    settingsElement.appendChild(xpathElement);
    xpathElement.setAttribute("version",xpathVersionButtonGroup.getSelection().getActionCommand());
    xpathElement.appendChild(document.createCDATASection(xpathQueryArea.getText()));
    TransformerFactory transformerFactory=TransformerFactory.newInstance();
    Transformer transformer=transformerFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.METHOD,"xml");
    transformer.setOutputProperty(OutputKeys.INDENT,"yes");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","3");
    transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
    Source source=new DOMSource(document);
    Result result=new StreamResult(Files.newBufferedWriter(new File(SETTINGS_FILE_NAME).toPath(),StandardCharsets.UTF_8));
    transformer.transform(source,result);
  }
 catch (  ParserConfigurationException|IOException|TransformerException e) {
    e.printStackTrace();
  }
}
