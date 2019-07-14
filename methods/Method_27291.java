public static void addHeader(@NonNull EditText editText,int level){
  String source=editText.getText().toString();
  int selectionStart=editText.getSelectionStart();
  int selectionEnd=editText.getSelectionEnd();
  StringBuilder result=new StringBuilder();
  String substring=source.substring(selectionStart,selectionEnd);
  if (!hasNewLine(source,selectionStart))   result.append("\n");
  IntStream.range(0,level).forEach(integer -> result.append("#"));
  result.append(" ").append(substring);
  editText.getText().replace(selectionStart,selectionEnd,result.toString());
  editText.setSelection(selectionStart + result.length());
}
