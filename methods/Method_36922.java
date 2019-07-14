/** 
 * Update a view's UI by its cell's data, you should change cell's data first.
 * @param cell
 * @since 3.0.0
 */
public void update(BaseCell cell){
  if (cell != null && mGroupBasicAdapter != null) {
    int position=mGroupBasicAdapter.getPositionByItem(cell);
    if (position >= 0) {
      try {
        cell.extras.put(ViewCache.Item.FLAG_INVALIDATE,true);
      }
 catch (      JSONException e) {
        e.printStackTrace();
      }
      mGroupBasicAdapter.notifyItemChanged(position);
    }
  }
}
