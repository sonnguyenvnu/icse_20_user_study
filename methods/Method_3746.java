private void resetAnimation(RecyclerView.ViewHolder holder){
  if (sDefaultInterpolator == null) {
    sDefaultInterpolator=new ValueAnimator().getInterpolator();
  }
  holder.itemView.animate().setInterpolator(sDefaultInterpolator);
  endAnimation(holder);
}
