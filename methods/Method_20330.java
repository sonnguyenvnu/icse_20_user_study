public boolean hasStateForHolder(EpoxyViewHolder holder){
  return get(holder.getItemId()) != null;
}
