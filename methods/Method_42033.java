@Override public void setNavBarColor(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    if (themeOnSingleImgAct())     if (isNavigationBarColored())     getWindow().setNavigationBarColor(ColorPalette.getTransparentColor(getPrimaryColor(),getTransparency()));
 else     getWindow().setNavigationBarColor(ColorPalette.getTransparentColor(ContextCompat.getColor(getApplicationContext(),R.color.md_black_1000),getTransparency()));
 else     getWindow().setNavigationBarColor(ColorPalette.getTransparentColor(ContextCompat.getColor(getApplicationContext(),R.color.md_black_1000),175));
  }
}
