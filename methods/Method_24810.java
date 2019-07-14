private void processControlSpace(final KeyEvent event){
  if (event.getKeyCode() == KeyEvent.VK_SPACE && event.isControlDown()) {
    if (JavaMode.codeCompletionsEnabled) {
      Messages.log("[KeyEvent]" + KeyEvent.getKeyText(event.getKeyCode()) + "  |Prediction started");
      fetchPhrase();
    }
  }
}
