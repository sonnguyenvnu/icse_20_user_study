/** 
 * Adds the given ViewHolder to the oldChangeHolders list
 * @param key The key to identify the ViewHolder.
 * @param holder The ViewHolder to store
 */
void addToOldChangeHolders(long key,RecyclerView.ViewHolder holder){
  mOldChangedHolders.put(key,holder);
}
