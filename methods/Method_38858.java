@Override public void xml(final CharSequence version,final CharSequence encoding,final CharSequence standalone){
  if (!enabled) {
    return;
  }
  XmlDeclaration xmlDeclaration=new XmlDeclaration(rootNode,version,encoding,standalone);
  parentNode.addChild(xmlDeclaration);
}
