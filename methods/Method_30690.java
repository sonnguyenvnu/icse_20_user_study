@Subscribe(threadMode=ThreadMode.POSTING) public void onReviewDeleted(ReviewDeletedEvent event){
  if (event.isFromMyself(this) || isEmpty()) {
    return;
  }
  List<SimpleReview> reviewList=get();
  for (int i=0, size=reviewList.size(); i < size; ) {
    SimpleReview review=reviewList.get(i);
    if (review.id == event.reviewId) {
      reviewList.remove(i);
      getListener().onReviewRemoved(getRequestCode(),i);
      --size;
    }
 else {
      ++i;
    }
  }
}
