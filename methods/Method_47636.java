private void initColors(){
  StyledResources res=new StyledResources(getContext());
  textColor=res.getColor(R.attr.mediumContrastTextColor);
  gridColor=res.getColor(R.attr.lowContrastTextColor);
  colors=new int[4];
  colors[0]=gridColor;
  colors[3]=primaryColor;
  colors[1]=ColorUtils.mixColors(colors[0],colors[3],0.66f);
  colors[2]=ColorUtils.mixColors(colors[0],colors[3],0.33f);
}
