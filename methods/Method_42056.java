public Media getFirstSelected(){
  if (selectedCount > 0) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)     return media.stream().filter(Media::isSelected).findFirst().orElse(null);
 else     for (    Media m : media)     if (m.isSelected())     return m;
  }
  return null;
}
