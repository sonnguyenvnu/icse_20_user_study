public static void addCode(@NonNull EditText editText){
  try {
    String source=editText.getText().toString();
    int selectionStart=editText.getSelectionStart();
    int selectionEnd=editText.getSelectionEnd();
    String substring=source.substring(selectionStart,selectionEnd);
    String result;
    if (hasNewLine(source,selectionStart))     result="```\n" + substring + "\n```\n";
 else     result="\n```\n" + substring + "\n```\n";
    editText.getText().replace(selectionStart,selectionEnd,result);
    editText.setSelection(result.length() + selectionStart - 5);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
