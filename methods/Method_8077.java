public void setIconAndTextAndValue(String text,int type,int value){
  currentType=type;
  currentColor=value;
  nameTextView.setText(text.substring(0,1).toUpperCase() + text.substring(1).toLowerCase());
  updateSelectedTintButton(false);
}
