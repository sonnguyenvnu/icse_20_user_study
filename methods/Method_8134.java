public void setUrl(CharSequence text,int searchLength){
  if (text != null) {
    SpannableStringBuilder builder=new SpannableStringBuilder(text);
    try {
      builder.setSpan(new ColorSpanUnderline(Theme.getColor(Theme.key_chat_emojiPanelStickerSetNameHighlight)),0,searchLength,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      builder.setSpan(new ColorSpanUnderline(Theme.getColor(Theme.key_chat_emojiPanelStickerSetName)),searchLength,text.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
 catch (    Exception ignore) {
    }
    urlTextView.setText(builder);
    urlTextView.setVisibility(VISIBLE);
  }
 else {
    urlTextView.setVisibility(GONE);
  }
}
