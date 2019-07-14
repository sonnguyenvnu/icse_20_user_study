/** 
 * Kickstart auto-complete suggestions 
 */
private void prepareSuggestions(final KeyEvent evt){
  if (JavaMode.codeCompletionsEnabled && (JavaMode.ccTriggerEnabled || (suggestion != null && suggestion.isVisible()))) {
    Messages.log("[KeyEvent]" + evt.getKeyChar() + "  |Prediction started");
    fetchPhrase();
  }
}
