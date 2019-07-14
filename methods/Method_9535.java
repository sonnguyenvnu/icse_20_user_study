private String getFixedString(String text){
  if (TextUtils.isEmpty(text)) {
    return text;
  }
  text=AndroidUtilities.getTrimmedString(text).toString();
  while (text.contains("\n\n\n")) {
    text=text.replace("\n\n\n","\n\n");
  }
  while (text.startsWith("\n\n\n")) {
    text=text.replace("\n\n\n","\n\n");
  }
  return text;
}
