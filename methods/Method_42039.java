@Override protected void setStatusBarColor(){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    getWindow().setNavigationBarColor(ColorPalette.getTransparentColor(ContextCompat.getColor(getApplicationContext(),org.horaapps.leafpic.R.color.md_black_1000),70));
  }
}
