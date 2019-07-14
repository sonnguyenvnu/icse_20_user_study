private void bindReviewListHolder(RecyclerView.ViewHolder holder,T item,List<SimpleReview> reviewList,View.OnClickListener onCreateListener,View.OnClickListener onViewMoreListener){
  ReviewListHolder reviewListHolder=(ReviewListHolder)holder;
  reviewListHolder.createButton.setOnClickListener(onCreateListener);
  reviewList=reviewList.subList(0,Math.min(ITEM_REVIEW_LIST_MAX_SIZE,reviewList.size()));
  ViewUtils.setVisibleOrGone(reviewListHolder.reviewList,!reviewList.isEmpty());
  ReviewListAdapter adapter=(ReviewListAdapter)reviewListHolder.reviewList.getAdapter();
  adapter.replace(reviewList);
  reviewListHolder.viewMoreButton.setOnClickListener(onViewMoreListener);
}
