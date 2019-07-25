private void change(BaseWrappedViewHolder holder,boolean isSelected){
  if (holder != null) {
    if (isSelected) {
      holder.itemView.setSelected(true);
    }
 else {
      holder.itemView.setSelected(false);
    }
  }
}
