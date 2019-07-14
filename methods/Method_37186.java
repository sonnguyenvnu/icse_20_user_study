/** 
 * Update a view's UI by its cell's data, you should change cell's data first.
 * @param cell
 * @since 3.0.0
 */
public void update(BaseCell cell){
  if (cell != null && mGroupBasicAdapter != null) {
    int position=mGroupBasicAdapter.getPositionByItem(cell);
    if (position >= 0) {
      cell.extras.put("_flag_invalidate_",true);
      mGroupBasicAdapter.notifyItemChanged(position);
    }
  }
}
