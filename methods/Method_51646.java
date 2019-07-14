private void copyXmlToClipboard(){
  String xml=getXmlTreeCode();
  if (xml != null) {
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(xml),this);
  }
}
