public void setUrl(CharSequence text,int searchLength){
  if (text != null) {
    SpannableStringBuilder builder=new SpannableStringBuilder(text);
    try {
      builder.setSpan(new ColorSpanUnderline(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4)),0,searchLength,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      builder.setSpan(new ColorSpanUnderline(Theme.getColor(Theme.key_chat_emojiPanelTrendingDescription)),searchLength,text.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
 catch (    Exception ignore) {
    }
    infoTextView.setText(builder);
  }
}
