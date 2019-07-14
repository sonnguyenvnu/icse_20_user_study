/** 
 * {@inheritDoc}
 */
@Override public void onViewRecycled(BinderViewHolder<C,? extends View> holder){
  if (mPerformanceMonitor != null) {
    mPerformanceMonitor.recordStart(PHASE_UNBIND,holder.itemView);
  }
  holder.unbind();
  if (mPerformanceMonitor != null) {
    mPerformanceMonitor.recordEnd(PHASE_UNBIND,holder.itemView);
  }
}
