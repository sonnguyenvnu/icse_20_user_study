@TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override public void setNavBarColor(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    getWindow().setNavigationBarColor(getPrimaryColor());
  }
}
