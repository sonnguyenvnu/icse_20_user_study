/** 
 * Adds the item information to the post layout list
 * @param holder The ViewHolder whose information is being saved
 * @param info The information to save
 */
void addToPostLayout(RecyclerView.ViewHolder holder,RecyclerView.ItemAnimator.ItemHolderInfo info){
  InfoRecord record=mLayoutHolderMap.get(holder);
  if (record == null) {
    record=InfoRecord.obtain();
    mLayoutHolderMap.put(holder,record);
  }
  record.postInfo=info;
  record.flags|=FLAG_POST;
}
