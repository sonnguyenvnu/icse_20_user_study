@Override public void onViewDetachedFromWindow(CompressedItemViewHolder holder){
  super.onViewAttachedToWindow(holder);
  holder.rl.clearAnimation();
  holder.txtTitle.setSelected(false);
}
