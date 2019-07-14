public static void addMentionString(EditText editText){
  Editable editable=editText.getText();
  int selectionStart=editText.getSelectionStart();
  int selectionEnd=editText.getSelectionEnd();
  int mentionStart;
  int mentionEnd;
  if (selectionStart > 0 && editable.charAt(selectionStart - 1) == '@') {
    mentionStart=selectionStart - 1;
    mentionEnd=selectionEnd;
    padSpaceAround(editText,mentionStart,mentionEnd);
  }
 else {
    editable.insert(selectionStart,"@");
    mentionStart=selectionStart;
    mentionEnd=selectionEnd + 1;
    if (selectionStart != selectionEnd) {
      int paddedMentionEnd=padSpaceAround(editText,mentionStart,mentionEnd);
      editText.setSelection(paddedMentionEnd);
    }
 else {
      editText.setSelection(mentionEnd);
      padSpaceAround(editText,mentionStart,mentionEnd);
    }
  }
}
