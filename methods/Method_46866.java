/** 
 * called when we would want to toggle check for all items in the adapter
 * @param b if to toggle true or false
 */
public void toggleChecked(boolean b){
  for (int i=0; i < itemsDigested.size(); i++) {
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
    if (mainFrag.mActionMode != null)     mainFrag.mActionMode.finish();
    mainFrag.mActionMode=null;
  }
}
