/** 
 * Removes a ViewHolder from disappearing list.
 * @param holder The ViewHolder to be removed from the disappearing list.
 */
void removeFromDisappearedInLayout(RecyclerView.ViewHolder holder){
  InfoRecord record=mLayoutHolderMap.get(holder);
  if (record == null) {
    return;
  }
  record.flags&=~FLAG_DISAPPEARED;
}
