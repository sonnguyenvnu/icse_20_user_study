public void setText(String text,boolean checked,boolean divider){
  textView.setText(text);
  radioButton.setChecked(checked,false);
  needDivider=divider;
  setWillNotDraw(!divider);
}
