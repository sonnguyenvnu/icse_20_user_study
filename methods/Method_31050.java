private static ColorStateList createNavigationItemTintList(int baseColorAttrRes,int primaryColor,Context context){
  ColorStateList baseColor=ViewUtils.getColorStateListFromAttrRes(baseColorAttrRes,context);
  int defaultColor=baseColor.getDefaultColor();
  return new ColorStateList(new int[][]{DISABLED_STATE_SET,CHECKED_STATE_SET,EMPTY_STATE_SET},new int[]{baseColor.getColorForState(DISABLED_STATE_SET,defaultColor),primaryColor,defaultColor});
}
