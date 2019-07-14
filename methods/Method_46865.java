public void toggleChecked(boolean b,String path){
  int i=path.equals("/") || !getBoolean(PREFERENCE_SHOW_GOBACK_BUTTON) ? 0 : 1;
  for (; i < itemsDigested.size(); i++) {
    ListItem item=itemsDigested.get(i);
    if (b && item.getChecked() != ListItem.CHECKED) {
      item.setChecked(true);
      notifyItemChanged(i);
    }
 else     if (!b && item.getChecked() == ListItem.CHECKED) {
      item.setChecked(false);
      notifyItemChanged(i);
    }
  }
  if (mainFrag.mActionMode != null) {
    mainFrag.mActionMode.invalidate();
  }
  if (getCheckedItems().size() == 0) {
    mainFrag.selection=false;
    if (mainFrag.mActionMode != null) {
      mainFrag.mActionMode.finish();
    }
    mainFrag.mActionMode=null;
  }
}
