public static void onSetSupportActionBar(Toolbar toolbar){
  Drawable icon=toolbar.getNavigationIcon();
  ColorStateList tintList=ViewUtils.getColorStateListFromAttrRes(R.attr.colorControlNormal,toolbar.getContext());
  icon=tintDrawable(icon,tintList);
  toolbar.setNavigationIcon(icon);
}
