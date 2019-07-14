public void setTextAndCheck(String text,boolean checked,boolean divider){
  textView.setText(text);
  checkBox.setChecked(checked,false);
  needDivider=divider;
  setWillNotDraw(!divider);
}
