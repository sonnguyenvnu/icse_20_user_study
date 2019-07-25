/** 
 * Should be called when the handler starts listening for key strokes. Adds this as a key stroke listener, adds to focus listeners of the underlying text widget of the editor, and sets the status line. This is added as a focus listener so that it can uninstall() itself if focus is lost.
 */
public void install(){
  editor.getViewer().prependVerifyKeyListener(this);
  editor.getViewer().getTextWidget().addFocusListener(this);
  editor.setStatusMessage("Example command.");
}
