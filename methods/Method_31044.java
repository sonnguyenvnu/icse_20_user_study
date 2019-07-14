public static void onPanelMenuCreated(int featureId,Menu menu,AppCompatActivity activity){
  if (featureId == Window.FEATURE_OPTIONS_PANEL) {
    Context context=activity.getSupportActionBar().getThemedContext();
    ColorStateList menuTintList=ViewUtils.getColorStateListFromAttrRes(R.attr.colorControlNormal,context);
    int popupThemeResId=ViewUtils.getResIdFromAttrRes(R.attr.popupTheme,0,context);
    ColorStateList subMenuTintList;
    if (popupThemeResId != 0) {
      Context popupContext=new ContextThemeWrapper(context,popupThemeResId);
      subMenuTintList=ViewUtils.getColorStateListFromAttrRes(R.attr.colorControlNormal,popupContext);
    }
 else {
      subMenuTintList=menuTintList;
    }
    tintMenuItemIcon(menu,menuTintList,subMenuTintList);
  }
}
