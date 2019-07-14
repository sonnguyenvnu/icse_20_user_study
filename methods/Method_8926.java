public void replaceWithText(int start,int len,CharSequence text,boolean parseEmoji){
  try {
    SpannableStringBuilder builder=new SpannableStringBuilder(messageEditText.getText());
    builder.replace(start,start + len,text);
    if (parseEmoji) {
      Emoji.replaceEmoji(builder,messageEditText.getPaint().getFontMetricsInt(),AndroidUtilities.dp(20),false);
    }
    messageEditText.setText(builder);
    if (start + text.length() <= messageEditText.length()) {
      messageEditText.setSelection(start + text.length());
    }
 else {
      messageEditText.setSelection(messageEditText.length());
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
