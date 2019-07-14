private void resetAnimation(ViewHolder holder){
  holder.itemView.animate().setInterpolator(sDefaultInterpolator);
  endAnimation(holder);
}
