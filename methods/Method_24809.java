/** 
 * Handles KeyEvents for TextArea (code completion begins from here). TODO Needs explanation of why this implemented with an override of processKeyEvent() instead of using listeners.
 */
@Override public void processKeyEvent(KeyEvent evt){
  if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
    if (suggestion != null) {
      if (suggestion.isVisible()) {
        Messages.log("ESC key");
        hideSuggestion();
        evt.consume();
        return;
      }
    }
  }
 else   if (evt.getKeyCode() == KeyEvent.VK_ENTER && evt.getID() == KeyEvent.KEY_PRESSED) {
    if (suggestion != null && suggestion.isVisible() && suggestion.insertSelection(CompletionPanel.KEYBOARD_COMPLETION)) {
      evt.consume();
      if (suggestion.isVisible()) {
        prepareSuggestions(evt);
      }
      return;
    }
  }
  if (evt.getID() == KeyEvent.KEY_PRESSED) {
switch (evt.getKeyCode()) {
case KeyEvent.VK_DOWN:
      if (suggestion != null)       if (suggestion.isVisible()) {
        suggestion.moveDown();
        return;
      }
    break;
case KeyEvent.VK_UP:
  if (suggestion != null)   if (suggestion.isVisible()) {
    suggestion.moveUp();
    return;
  }
break;
case KeyEvent.VK_BACK_SPACE:
Messages.log("BK Key");
break;
case KeyEvent.VK_SPACE:
if (suggestion != null) {
if (suggestion.isVisible()) {
Messages.log("Space bar, hide completion list");
suggestion.setInvisible();
}
}
break;
}
}
super.processKeyEvent(evt);
if (!getJavaEditor().hasJavaTabs()) {
if (evt.getID() == KeyEvent.KEY_TYPED) {
processCompletionKeys(evt);
}
 else if (!Platform.isMacOS() && evt.getID() == KeyEvent.KEY_RELEASED) {
processCompletionKeys(evt);
}
 else if (Platform.isMacOS() && evt.getID() == KeyEvent.KEY_RELEASED) {
processControlSpace(evt);
}
}
}
