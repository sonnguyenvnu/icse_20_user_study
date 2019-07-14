public static void addQuote(@NonNull EditText editText){
  String source=editText.getText().toString();
  int selectionStart=editText.getSelectionStart();
  int selectionEnd=editText.getSelectionEnd();
  String substring=source.substring(selectionStart,selectionEnd);
  String result;
  if (hasNewLine(source,selectionStart)) {
    result="> " + substring;
  }
 else {
    result="\n> " + substring;
  }
  editText.getText().replace(selectionStart,selectionEnd,result);
  editText.setSelection(result.length() + selectionStart);
}
