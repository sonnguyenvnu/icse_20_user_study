/** 
 * Creates item layouts if necessary
 */
private void createItemsLayout(){
  if (itemsLayout == null) {
    itemsLayout=new LinearLayout(getContext());
    itemsLayout.setOrientation(LinearLayout.VERTICAL);
  }
}
