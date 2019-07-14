private void updatePaintSize(){
  ApplicationLoader.applicationContext.getSharedPreferences("articles",Activity.MODE_PRIVATE).edit().putInt("font_size",selectedFontSize).commit();
  for (int i=0; i < 2; i++) {
    adapter[i].notifyDataSetChanged();
  }
}
