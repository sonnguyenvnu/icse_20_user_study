/** 
 * Should be called when the handler is done listening for key strokes. Removes itself as a key stroke listener, a focus listener, and sets the status line to the empty string.
 */
private void uninstall(){
  System.out.println("Start uninstall");
  editor.getViewer().removeVerifyKeyListener(this);
  editor.getViewer().getTextWidget().removeFocusListener(this);
  editor.setStatusMessage("");
  System.out.println("End uninstall");
}
