/** 
 * Inserts the CompletionCandidate chosen from the suggestion list
 * @param completionSource - whether being completed via keypress or mouse click.
 * @return true - if code was successfully inserted at the caret position
 */
protected boolean insertSelection(int completionSource){
  if (completionList.getSelectedValue() != null) {
    try {
      String currentSubword=fetchCurrentSubword();
      int currentSubwordLen=(currentSubword == null) ? 0 : currentSubword.length();
      String selectedSuggestion=completionList.getSelectedValue().getCompletionString();
      if (currentSubword != null) {
        selectedSuggestion=selectedSuggestion.substring(currentSubwordLen);
      }
 else {
        currentSubword="";
      }
      String completionString=completionList.getSelectedValue().getCompletionString();
      if (selectedSuggestion.endsWith(" )")) {
        if (completionString.endsWith(" )")) {
          completionString=completionString.substring(0,completionString.length() - 2) + ")";
        }
      }
      boolean mouseClickOnOverloadedMethods=false;
      if (completionSource == MOUSE_COMPLETION) {
        if (completionString.endsWith("(")) {
          mouseClickOnOverloadedMethods=true;
        }
      }
      Messages.loge(subWord + " <= subword, Inserting suggestion=> " + selectedSuggestion + " Current sub: " + currentSubword);
      if (currentSubword.length() > 0) {
        textarea.getDocument().remove(insertionPosition - currentSubwordLen,currentSubwordLen);
      }
      textarea.getDocument().insertString(insertionPosition - currentSubwordLen,completionString,null);
      if (selectedSuggestion.endsWith(")") && !selectedSuggestion.endsWith("()")) {
        int x=selectedSuggestion.indexOf(',');
        if (x == -1) {
          textarea.setCaretPosition(textarea.getCaretPosition() - 1);
        }
 else {
          textarea.setCaretPosition(insertionPosition + x);
        }
      }
      Messages.log("Suggestion inserted: " + System.currentTimeMillis());
      if (completionList.getSelectedValue().getLabel().contains("...")) {
      }
 else {
        setInvisible();
      }
      if (mouseClickOnOverloadedMethods) {
        ((JavaTextArea)editor.getTextArea()).fetchPhrase();
      }
      return true;
    }
 catch (    BadLocationException e1) {
      e1.printStackTrace();
    }
catch (    Exception e) {
      e.printStackTrace();
    }
    setInvisible();
  }
  return false;
}
