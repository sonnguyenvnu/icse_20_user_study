public void setText(String text,boolean divider){
  textView.setText(text);
  needDivider=divider;
  setWillNotDraw(!divider);
}
