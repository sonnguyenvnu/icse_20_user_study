private void init(){
  StyledResources sr=new StyledResources(getContext());
  exampleColor=sr.getColor(R.attr.mediumContrastTextColor);
  setOnFocusChangeListener(this);
  updateText();
}
