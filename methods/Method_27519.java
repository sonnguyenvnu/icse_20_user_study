@Nullable private View getToolbarNavigationIcon(Toolbar toolbar){
  boolean hadContentDescription=TextUtils.isEmpty(toolbar.getNavigationContentDescription());
  String contentDescription=!hadContentDescription ? String.valueOf(toolbar.getNavigationContentDescription()) : "navigationIcon";
  toolbar.setNavigationContentDescription(contentDescription);
  ArrayList<View> potentialViews=new ArrayList<>();
  toolbar.findViewsWithText(potentialViews,contentDescription,View.FIND_VIEWS_WITH_CONTENT_DESCRIPTION);
  View navIcon=null;
  if (potentialViews.size() > 0) {
    navIcon=potentialViews.get(0);
  }
  if (hadContentDescription)   toolbar.setNavigationContentDescription(null);
  return navIcon;
}
