private void initColors(){
  int red=Color.red(primaryColor);
  int green=Color.green(primaryColor);
  int blue=Color.blue(primaryColor);
  StyledResources res=new StyledResources(getContext());
  colors=new int[4];
  colors[3]=primaryColor;
  colors[2]=Color.argb(192,red,green,blue);
  colors[1]=Color.argb(96,red,green,blue);
  colors[0]=res.getColor(R.attr.lowContrastTextColor);
  textColor=res.getColor(R.attr.mediumContrastTextColor);
  reverseTextColor=res.getColor(R.attr.highContrastReverseTextColor);
}
