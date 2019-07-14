public static void addDivider(@NonNull EditText editText){
  String source=editText.getText().toString();
  int selectionStart=editText.getSelectionStart();
  String result;
  if (hasNewLine(source,selectionStart)) {
    result="-------\n";
  }
 else {
    result="\n-------\n";
  }
  editText.getText().replace(selectionStart,selectionStart,result);
  editText.setSelection(result.length() + selectionStart);
}
