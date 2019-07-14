private void initEditMode(){
  int color=PaletteUtils.getAndroidTestColor(1);
  title.setTextColor(color);
  chart.setColor(color);
  chart.populateWithRandomData();
}
