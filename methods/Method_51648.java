private void loadSettings(){
  File file=new File(SETTINGS_FILE_NAME);
  if (file.exists()) {
    try (InputStream stream=Files.newInputStream(file.toPath())){
      DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
      Document document=builder.parse(stream);
      Element settingsElement=document.getDocumentElement();
      Element codeElement=(Element)settingsElement.getElementsByTagName("code").item(0);
      Element xpathElement=(Element)settingsElement.getElementsByTagName("xpath").item(0);
      String code=getTextContext(codeElement);
      String languageVersion=codeElement.getAttribute("language-version");
      String xpath=getTextContext(xpathElement);
      String xpathVersion=xpathElement.getAttribute("version");
      codeEditorPane.setText(code);
      setLanguageVersion(LanguageRegistry.findLanguageVersionByTerseName(languageVersion));
      xpathQueryArea.setText(xpath);
      for (Enumeration<AbstractButton> e=xpathVersionButtonGroup.getElements(); e.hasMoreElements(); ) {
        AbstractButton button=e.nextElement();
        if (xpathVersion.equals(button.getActionCommand())) {
          button.setSelected(true);
          break;
        }
      }
    }
 catch (    ParserConfigurationException|IOException|SAXException e) {
      e.printStackTrace();
    }
  }
}
