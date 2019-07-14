private static CustomTabsIntent makeCustomTabsIntent(Context context){
  return new CustomTabsIntent.Builder().addDefaultShareMenuItem().enableUrlBarHiding().setToolbarColor(ViewUtils.getColorFromAttrRes(R.attr.colorPrimary,0,context)).setShowTitle(true).build();
}
