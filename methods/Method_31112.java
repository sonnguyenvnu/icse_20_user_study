public static void setTextViewLinkClickableAndTextSelectable(TextView textView){
  textView.setTextIsSelectable(true);
  textView.setMovementMethod(LinkArrowKeyMovementMethod.getInstance());
}
