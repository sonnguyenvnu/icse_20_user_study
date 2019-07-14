public void onCreateViewHolder(ViewGroup parent,View itemView){
  RecyclerView.LayoutParams lp=(RecyclerView.LayoutParams)itemView.getLayoutParams();
  lp.width=parent.getWidth() - ScreenUtil.dip2px(itemView.getContext(),2 * (sPagePadding + sShowLeftCardWidth));
  itemView.setLayoutParams(lp);
}
