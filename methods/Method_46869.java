/** 
 * Adds item to the end of the list, don't use this unless you are dynamically loading the adapter, after you are finished you must call createHeaders
 */
public void addItem(LayoutElementParcelable e){
  if (mainFrag.IS_LIST && itemsDigested.size() > 0) {
    itemsDigested.add(itemsDigested.size() - 1,new ListItem(e));
  }
 else   if (mainFrag.IS_LIST) {
    itemsDigested.add(new ListItem(e));
    itemsDigested.add(new ListItem(EMPTY_LAST_ITEM));
  }
 else {
    itemsDigested.add(new ListItem(e));
  }
  notifyItemInserted(getItemCount());
}
