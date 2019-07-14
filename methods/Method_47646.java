private void initColors(){
  StyledResources res=new StyledResources(getContext());
  if (isBackgroundTransparent)   primaryColor=ColorUtils.setMinValue(primaryColor,0.75f);
  int red=Color.red(primaryColor);
  int green=Color.green(primaryColor);
  int blue=Color.blue(primaryColor);
  if (isBackgroundTransparent) {
    colors=new int[3];
    colors[0]=Color.argb(16,255,255,255);
    colors[1]=Color.argb(128,red,green,blue);
    colors[2]=primaryColor;
    textColor=Color.WHITE;
    reverseTextColor=Color.WHITE;
  }
 else {
    colors=new int[3];
    colors[0]=res.getColor(R.attr.lowContrastTextColor);
    colors[1]=Color.argb(127,red,green,blue);
    colors[2]=primaryColor;
    textColor=res.getColor(R.attr.mediumContrastTextColor);
    reverseTextColor=res.getColor(R.attr.highContrastReverseTextColor);
  }
}
