public boolean areAllChecked(String path){
  boolean allChecked=true;
  int i=(path.equals("/") || !getBoolean(PREFERENCE_SHOW_GOBACK_BUTTON)) ? 0 : 1;
  for (; i < itemsDigested.size(); i++) {
    if (itemsDigested.get(i).getChecked() == ListItem.NOT_CHECKED) {
      allChecked=false;
    }
  }
  return allChecked;
}
