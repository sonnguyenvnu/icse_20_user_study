/** 
 * A ViewHolder might be added by the LayoutManager just to animate its disappearance. This list holds such items so that we can animate / recycle these ViewHolders properly.
 * @param holder The ViewHolder which disappeared during a layout.
 */
void addToDisappearedInLayout(RecyclerView.ViewHolder holder){
  InfoRecord record=mLayoutHolderMap.get(holder);
  if (record == null) {
    record=InfoRecord.obtain();
    mLayoutHolderMap.put(holder,record);
  }
  record.flags|=FLAG_DISAPPEARED;
}
