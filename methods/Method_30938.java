public static void addTopicString(EditText editText){
  Editable editable=editText.getText();
  int selectionStart=editText.getSelectionStart();
  int selectionEnd=editText.getSelectionEnd();
  int topicStart;
  int topicEnd;
  if (selectionStart != selectionEnd) {
    if (selectionStart > 0 && editable.charAt(selectionStart - 1) == '#' && selectionEnd < editable.length() && editable.charAt(selectionEnd) == '#') {
      topicStart=selectionStart - 1;
      topicEnd=selectionEnd + 1;
      padSpaceAround(editText,topicStart,topicEnd);
    }
 else {
      editable.insert(selectionStart,"#");
      editable.insert(selectionEnd + 1,"#");
      topicStart=selectionStart;
      topicEnd=selectionEnd + 2;
      int paddedTopicEnd=padSpaceAround(editText,topicStart,topicEnd);
      editText.setSelection(paddedTopicEnd);
    }
  }
 else {
    int length=editable.length();
    editable.insert(length,"#????#");
    topicStart=length;
    topicEnd=length + 6;
    editText.setSelection(topicStart + 1,topicEnd - 1);
    padSpaceAround(editText,topicStart,topicEnd);
  }
}
