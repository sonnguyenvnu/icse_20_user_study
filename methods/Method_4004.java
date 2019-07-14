void repositionShadowingViews(){
  int count=mChildHelper.getChildCount();
  for (int i=0; i < count; i++) {
    View view=mChildHelper.getChildAt(i);
    ViewHolder holder=getChildViewHolder(view);
    if (holder != null && holder.mShadowingHolder != null) {
      View shadowingView=holder.mShadowingHolder.itemView;
      int left=view.getLeft();
      int top=view.getTop();
      if (left != shadowingView.getLeft() || top != shadowingView.getTop()) {
        shadowingView.layout(left,top,left + shadowingView.getWidth(),top + shadowingView.getHeight());
      }
    }
  }
}
