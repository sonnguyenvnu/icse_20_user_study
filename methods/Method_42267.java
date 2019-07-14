private void selectItem(@NonNull NavigationEntry v){
  selectedEntry.setBackground(null);
  selectedEntry=v;
  selectedEntry.setBackgroundColor(selectedColor);
}
