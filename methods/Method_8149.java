public void setText(CharSequence text){
  if (text == null) {
    textView.setPadding(0,AndroidUtilities.dp(2),0,0);
  }
 else {
    textView.setPadding(0,AndroidUtilities.dp(10),0,AndroidUtilities.dp(bottomPadding));
  }
  textView.setText(text);
}
