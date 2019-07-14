@Override public void onBindViewHolder(BinderViewHolder<C,? extends View> holder,int position){
  C data=mData.get(position);
  if (mPerformanceMonitor != null) {
    mPerformanceMonitor.recordStart(PHASE_BIND,holder.itemView);
  }
  holder.bind(data);
  if (mPerformanceMonitor != null) {
    mPerformanceMonitor.recordEnd(PHASE_BIND,holder.itemView);
  }
}
