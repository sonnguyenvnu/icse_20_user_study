public void onBindViewHolder(View itemView,final int position,int itemCount){
  int padding=ScreenUtil.dip2px(itemView.getContext(),sPagePadding);
  itemView.setPadding(padding,0,padding,0);
  int leftMarin=position == 0 ? padding + ScreenUtil.dip2px(itemView.getContext(),sShowLeftCardWidth) : 0;
  int rightMarin=position == itemCount - 1 ? padding + ScreenUtil.dip2px(itemView.getContext(),sShowLeftCardWidth) : 0;
  setViewMargin(itemView,leftMarin,0,rightMarin,0);
}
