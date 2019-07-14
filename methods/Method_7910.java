public void setTextAndValue(String text,String value,boolean parseLinks){
  if (TextUtils.isEmpty(text) || text != null && oldText != null && text.equals(oldText)) {
    return;
  }
  oldText=text;
  stringBuilder=new SpannableStringBuilder(oldText);
  if (parseLinks) {
    MessageObject.addLinks(false,stringBuilder,false);
  }
  Emoji.replaceEmoji(stringBuilder,Theme.profile_aboutTextPaint.getFontMetricsInt(),AndroidUtilities.dp(20),false);
  if (TextUtils.isEmpty(value)) {
    valueTextView.setVisibility(GONE);
  }
 else {
    valueTextView.setText(value);
    valueTextView.setVisibility(VISIBLE);
  }
  requestLayout();
}
