public void setMultiline(boolean value){
  isMultiline=value;
  LayoutParams layoutParams=(LayoutParams)textView.getLayoutParams();
  LayoutParams layoutParams1=(LayoutParams)checkBox.getLayoutParams();
  if (isMultiline) {
    textView.setLines(0);
    textView.setMaxLines(0);
    textView.setSingleLine(false);
    textView.setEllipsize(null);
    textView.setPadding(0,0,0,AndroidUtilities.dp(5));
    layoutParams.height=LayoutParams.WRAP_CONTENT;
    layoutParams.topMargin=AndroidUtilities.dp(10);
    layoutParams1.topMargin=AndroidUtilities.dp(12);
  }
 else {
    textView.setLines(1);
    textView.setMaxLines(1);
    textView.setSingleLine(true);
    textView.setEllipsize(TextUtils.TruncateAt.END);
    textView.setPadding(0,0,0,0);
    layoutParams.height=LayoutParams.MATCH_PARENT;
    layoutParams.topMargin=0;
    layoutParams1.topMargin=AndroidUtilities.dp(15);
  }
  textView.setLayoutParams(layoutParams);
  checkBox.setLayoutParams(layoutParams1);
}
