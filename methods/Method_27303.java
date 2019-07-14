public static void insertAtCursor(@NonNull EditText editText,@NonNull String text){
  String oriContent=editText.getText().toString();
  int start=editText.getSelectionStart();
  int end=editText.getSelectionEnd();
  Logger.e(start,end);
  if (start >= 0 && end > 0 && start != end) {
    editText.setText(editText.getText().replace(start,end,text));
  }
 else {
    int index=editText.getSelectionStart() >= 0 ? editText.getSelectionStart() : 0;
    Logger.e(start,end,index);
    StringBuilder builder=new StringBuilder(oriContent);
    builder.insert(index,text);
    editText.setText(builder.toString());
    editText.setSelection(index + text.length());
  }
}
