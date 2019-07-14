public static void addItalic(@NonNull EditText editText){
  String source=editText.getText().toString();
  int selectionStart=editText.getSelectionStart();
  int selectionEnd=editText.getSelectionEnd();
  String substring=source.substring(selectionStart,selectionEnd);
  String result="_" + substring + "_ ";
  editText.getText().replace(selectionStart,selectionEnd,result);
  editText.setSelection(result.length() + selectionStart - 2);
}
