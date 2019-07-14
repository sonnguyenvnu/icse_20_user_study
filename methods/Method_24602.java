/** 
 * (Re-)paint this line highlight.
 */
public void paint(){
  if (editor.isInCurrentTab(lineID)) {
    if (marker != null) {
      editor.getJavaTextArea().setGutterText(lineID.lineIdx(),marker);
    }
  }
}
