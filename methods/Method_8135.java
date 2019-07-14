public void setText(CharSequence text,int resId,int index,int searchLength){
  if (text == null) {
    empty=true;
    textView.setText("");
    buttonView.setVisibility(INVISIBLE);
  }
 else {
    if (searchLength != 0) {
      SpannableStringBuilder builder=new SpannableStringBuilder(text);
      try {
        builder.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_chat_emojiPanelStickerSetNameHighlight)),index,index + searchLength,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
 catch (      Exception ignore) {
      }
      textView.setText(builder);
    }
 else {
      textView.setText(Emoji.replaceEmoji(text,textView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(14),false));
    }
    if (resId != 0) {
      buttonView.setImageResource(resId);
      buttonView.setVisibility(VISIBLE);
    }
 else {
      buttonView.setVisibility(INVISIBLE);
    }
  }
}
