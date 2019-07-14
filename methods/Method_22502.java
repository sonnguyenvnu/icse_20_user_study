static void setSelectionStyle(JTextPane textPane,boolean selected){
  Document doc=textPane.getDocument();
  if (doc instanceof HTMLDocument) {
    HTMLDocument html=(HTMLDocument)doc;
    StyleSheet styleSheet=html.getStyleSheet();
    if (selected) {
      styleSheet.addRule("a { text-decoration:underline } ");
    }
 else {
      styleSheet.addRule("a { text-decoration:none }");
    }
  }
}
