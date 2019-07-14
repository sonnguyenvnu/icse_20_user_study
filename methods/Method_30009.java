@Override public void onViewRecycled(@NonNull ViewHolder holder){
  holder.broadcastLayout.unbind();
}
