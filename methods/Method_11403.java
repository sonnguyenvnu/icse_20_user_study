private void dispatchItemClickListener(final BaseViewHolder vh){
  if (mOnItemClickListeners != null && mOnItemClickListeners.size() > 0) {
    if (!(vh.itemView instanceof AdapterView)) {
      vh.itemView.setOnClickListener(new View.OnClickListener(){
        @Override public void onClick(        View v){
          for (int i=0; i < mOnItemClickListeners.size(); i++) {
            final OnItemClickListener listener=mOnItemClickListeners.get(i);
            listener.onItemClick(vh,vh.getLayoutPosition() - getHeaderViewCount());
          }
        }
      }
);
    }
  }
  if (mOnItemLongClickListeners != null && mOnItemLongClickListeners.size() > 0) {
    if (!(vh.itemView instanceof AdapterView)) {
      vh.itemView.setOnLongClickListener(new View.OnLongClickListener(){
        @Override public boolean onLongClick(        View v){
          for (int i=0; i < mOnItemLongClickListeners.size(); i++) {
            final OnItemLongClickListener listener=mOnItemLongClickListeners.get(i);
            listener.onItemLongClick(vh,vh.getLayoutPosition() - getHeaderViewCount());
          }
          return true;
        }
      }
);
    }
  }
}
