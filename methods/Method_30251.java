protected void bindReviewListHolder(RecyclerView.ViewHolder holder,T item,List<SimpleReview> reviewList,int titleRes,int createRes,int viewMoreRes,View.OnClickListener onCreateListener,View.OnClickListener onViewMoreListener){
  ReviewListHolder reviewListHolder=(ReviewListHolder)holder;
  reviewListHolder.titleText.setText(titleRes);
  reviewListHolder.createButton.setText(createRes);
  reviewListHolder.viewMoreButton.setText(viewMoreRes);
  bindReviewListHolder(holder,item,reviewList,onCreateListener,onViewMoreListener);
}
