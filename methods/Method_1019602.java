/** 
 * Installs this listener on a text area.  If it is already installed on another text area, it is uninstalled first.
 * @param textArea The text area to install on.
 */
public void install(RSyntaxTextArea textArea){
  if (this.textArea != null) {
    uninstall();
  }
  this.textArea=textArea;
  textArea.addCaretListener(this);
  if (textArea.getMarkOccurrencesColor() != null) {
    setColor(textArea.getMarkOccurrencesColor());
  }
}
