/** 
 * {@inheritDoc}
 * @return True if change animations are not supported or the ViewHolder is invalid,false otherwise.
 * @see #setSupportsChangeAnimations(boolean)
 */
@Override public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder){
  return !mSupportsChangeAnimations || viewHolder.isInvalid();
}
