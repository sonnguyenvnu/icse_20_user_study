private void recyclerView(List<BinderViewHolder> cache){
  if (!cache.isEmpty()) {
    RecyclerView.RecycledViewPool pool=cell.serviceManager.getService(RecyclerView.RecycledViewPool.class);
    for (int i=0, size=cache.size(); i < size; i++) {
      BinderViewHolder viewHolder=cache.get(i);
      viewHolder.unbind();
      removeView(viewHolder.itemView);
      pool.putRecycledView(viewHolder);
    }
    cache.clear();
  }
}
