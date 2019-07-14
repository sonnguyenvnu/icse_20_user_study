public void setValue(String name,boolean checked,boolean divider){
  textView.setText(name);
  checkImage.setVisibility(checked ? VISIBLE : INVISIBLE);
  needDivider=divider;
}
