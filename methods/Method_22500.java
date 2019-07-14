/** 
 * Sets coloring based on whether installed or not; also makes ugly blue HTML links into the specified color (black).
 */
static void setForegroundStyle(JTextPane textPane,boolean installed,boolean selected){
  Document doc=textPane.getDocument();
  if (doc instanceof HTMLDocument) {
    HTMLDocument html=(HTMLDocument)doc;
    StyleSheet stylesheet=html.getStyleSheet();
    String c=(installed && !selected) ? "#555555" : "#000000";
    stylesheet.addRule("body { color:" + c + "; }");
    stylesheet.addRule("a { color:" + c + "; }");
  }
}
