public void setTextAndCheck(String text,boolean checked,boolean divider){
  textView.setText(text);
  isMultiline=false;
  checkBox.setChecked(checked,false);
  needDivider=divider;
  valueTextView.setVisibility(GONE);
  LayoutParams layoutParams=(LayoutParams)textView.getLayoutParams();
  layoutParams.height=LayoutParams.MATCH_PARENT;
  layoutParams.topMargin=0;
  textView.setLayoutParams(layoutParams);
  setWillNotDraw(!divider);
}
