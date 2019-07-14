private void createPinnedHeader(RecyclerView parent){
  updatePinnedHeader(parent);
  RecyclerView.LayoutManager layoutManager=parent.getLayoutManager();
  if (layoutManager == null || layoutManager.getChildCount() <= 0) {
    return;
  }
  int firstVisiblePosition=((RecyclerView.LayoutParams)layoutManager.getChildAt(0).getLayoutParams()).getViewAdapterPosition();
  int headerPosition=findPinnedHeaderPosition(parent,firstVisiblePosition);
  if (headerPosition >= 0 && mHeaderPosition != headerPosition) {
    mHeaderPosition=headerPosition;
    int viewType=mAdapter.getItemViewType(headerPosition);
    RecyclerView.ViewHolder pinnedViewHolder=mAdapter.createViewHolder(parent,viewType);
    mAdapter.bindViewHolder(pinnedViewHolder,headerPosition);
    mPinnedHeaderView=pinnedViewHolder.itemView;
    ViewGroup.LayoutParams layoutParams=mPinnedHeaderView.getLayoutParams();
    if (layoutParams == null) {
      layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
      mPinnedHeaderView.setLayoutParams(layoutParams);
    }
    int heightMode=View.MeasureSpec.getMode(layoutParams.height);
    int heightSize=View.MeasureSpec.getSize(layoutParams.height);
    if (heightMode == View.MeasureSpec.UNSPECIFIED) {
      heightMode=View.MeasureSpec.EXACTLY;
    }
    int maxHeight=parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
    if (heightSize > maxHeight) {
      heightSize=maxHeight;
    }
    int ws=View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(),View.MeasureSpec.EXACTLY);
    int hs=View.MeasureSpec.makeMeasureSpec(heightSize,heightMode);
    mPinnedHeaderView.measure(ws,hs);
    mPinnedHeaderView.layout(0,0,mPinnedHeaderView.getMeasuredWidth(),mPinnedHeaderView.getMeasuredHeight());
  }
}
