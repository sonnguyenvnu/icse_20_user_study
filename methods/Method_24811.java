private void processCompletionKeys(final KeyEvent event){
  char keyChar=event.getKeyChar();
  int keyCode=event.getKeyCode();
  if (keyChar == KeyEvent.VK_ENTER || keyChar == KeyEvent.VK_ESCAPE || keyChar == KeyEvent.VK_TAB || (event.getID() == KeyEvent.KEY_RELEASED && keyCode != KeyEvent.VK_LEFT && keyCode != KeyEvent.VK_RIGHT)) {
  }
 else   if (keyChar == ')') {
    hideSuggestion();
  }
 else   if (keyChar == '.') {
    if (JavaMode.codeCompletionsEnabled) {
      Messages.log("[KeyEvent]" + KeyEvent.getKeyText(event.getKeyCode()) + "  |Prediction started");
      fetchPhrase();
    }
  }
 else   if (keyChar == ' ') {
    if (!Platform.isMacOS() && JavaMode.codeCompletionsEnabled && (event.isControlDown() || event.isMetaDown())) {
      if (JavaMode.codeCompletionsEnabled) {
        Messages.log("[KeyEvent]" + event.getKeyChar() + "  |Prediction started");
        fetchPhrase();
      }
    }
 else {
      hideSuggestion();
    }
  }
 else {
    if (JavaMode.codeCompletionsEnabled) {
      prepareSuggestions(event);
    }
  }
}
