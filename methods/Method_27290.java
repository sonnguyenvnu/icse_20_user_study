public static void addList(@NonNull EditText editText,@NonNull String list){
  String tag=list + " ";
  String source=editText.getText().toString();
  int selectionStart=editText.getSelectionStart();
  int selectionEnd=editText.getSelectionEnd();
  String substring=source.substring(0,selectionStart);
  int line=substring.lastIndexOf(10);
  if (line != -1) {
    selectionStart=line + 1;
  }
 else {
    selectionStart=0;
  }
  substring=source.substring(selectionStart,selectionEnd);
  String[] split=substring.split("\n");
  StringBuilder stringBuffer=new StringBuilder();
  if (split.length > 0)   for (  String s : split) {
    if (s.length() == 0 && stringBuffer.length() != 0) {
      stringBuffer.append("\n");
      continue;
    }
    if (!s.trim().startsWith(tag)) {
      if (stringBuffer.length() > 0)       stringBuffer.append("\n");
      stringBuffer.append(tag).append(s);
    }
 else {
      if (stringBuffer.length() > 0)       stringBuffer.append("\n");
      stringBuffer.append(s);
    }
  }
  if (stringBuffer.length() == 0) {
    stringBuffer.append(tag);
  }
  editText.getText().replace(selectionStart,selectionEnd,stringBuffer.toString());
  editText.setSelection(stringBuffer.length() + selectionStart);
}
