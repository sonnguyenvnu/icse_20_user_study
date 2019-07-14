private void initColors(){
  StyledResources res=new StyledResources(getContext());
  primaryColor=Color.BLACK;
  textColor=res.getColor(R.attr.mediumContrastTextColor);
  gridColor=res.getColor(R.attr.lowContrastTextColor);
  backgroundColor=res.getColor(R.attr.cardBackgroundColor);
}
