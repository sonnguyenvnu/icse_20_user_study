public static void addBold(@NonNull EditText editText){
  String source=editText.getText().toString();
  int selectionStart=editText.getSelectionStart();
  int selectionEnd=editText.getSelectionEnd();
  String substring=source.substring(selectionStart,selectionEnd);
  String result="**" + substring + "** ";
  editText.getText().replace(selectionStart,selectionEnd,result);
  editText.setSelection(result.length() + selectionStart - 3);
}
