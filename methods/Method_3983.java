/** 
 * Returns a unique key to be used while handling change animations. It might be child's position or stable id depending on the adapter type.
 */
long getChangedHolderKey(ViewHolder holder){
  return mAdapter.hasStableIds() ? holder.getItemId() : holder.mPosition;
}
