private void updateRowsSelection(){
  int count=listView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=listView.getChildAt(a);
    if (child instanceof WallpaperCell) {
      WallpaperCell cell=(WallpaperCell)child;
      for (int b=0; b < 5; b++) {
        cell.setChecked(b,false,true);
      }
    }
  }
}
