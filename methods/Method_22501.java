static void setTextStyle(JTextPane textPane,String fontSize){
  Document doc=textPane.getDocument();
  if (doc instanceof HTMLDocument) {
    HTMLDocument html=(HTMLDocument)doc;
    StyleSheet stylesheet=html.getStyleSheet();
    stylesheet.addRule("body { " + "  margin: 0; padding: 0;" + "  font-family: " + Toolkit.getSansFontName() + ", Arial, Helvetica, sans-serif;" + "  font-size: 100%;" + "font-size: " + fontSize + "; " + "}");
  }
}
