private void setupActionBarColor(){
  StyledResources res=new StyledResources(this);
  int color=BaseScreen.getDefaultActionBarColor(this);
  if (res.getBoolean(R.attr.useHabitColorAsPrimary))   color=res.getColor(R.attr.aboutScreenColor);
  BaseScreen.setupActionBarColor(this,color);
}
