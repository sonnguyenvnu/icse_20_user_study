@Override public void setNavBarColor(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    getWindow().setStatusBarColor(ColorPalette.getTransparentColor(ContextCompat.getColor(getApplicationContext(),org.horaapps.leafpic.R.color.md_black_1000),70));
  }
}
