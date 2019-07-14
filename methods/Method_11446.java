/** 
 * Creates item layouts if necessary
 */
@Override protected void createItemsLayout(){
  if (mItemsLayout == null) {
    mItemsLayout=new LinearLayout(getContext());
    mItemsLayout.setOrientation(LinearLayout.HORIZONTAL);
  }
}
