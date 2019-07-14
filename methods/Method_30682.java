public void bind(final User user,List<SimpleReview> reviewList){
  final Context context=getContext();
  OnClickListener viewMoreListener=new OnClickListener(){
    @Override public void onClick(    View view){
      UriHandler.open(StringUtils.formatUs("https://www.douban.com/people/%s/reviews",user.getIdOrUid()),context);
    }
  }
;
  mTitleText.setOnClickListener(viewMoreListener);
  mViewMoreText.setOnClickListener(viewMoreListener);
  int i=0;
  for (  final SimpleReview review : reviewList) {
    if (i >= REVIEW_COUNT_MAX) {
      break;
    }
    if (i >= mReviewList.getChildCount()) {
      LayoutInflater.from(context).inflate(R.layout.profile_review_item,mReviewList);
    }
    View reviewLayout=mReviewList.getChildAt(i);
    ReviewLayoutHolder holder=(ReviewLayoutHolder)reviewLayout.getTag();
    if (holder == null) {
      holder=new ReviewLayoutHolder(reviewLayout);
      reviewLayout.setTag(holder);
      ViewUtils.setTextViewLinkClickable(holder.titleText);
    }
    String coverUrl=review.coverUrl;
    if (TextUtils.isEmpty(coverUrl) && review.item != null && review.item.cover != null) {
      coverUrl=review.item.cover.getMediumUrl();
    }
    if (!TextUtils.isEmpty(coverUrl)) {
      holder.coverImage.setVisibility(VISIBLE);
      ImageUtils.loadImage(holder.coverImage,coverUrl);
    }
 else {
      holder.coverImage.setVisibility(GONE);
    }
    holder.titleText.setText(review.title);
    holder.abstractText.setText(review.abstract_);
    reviewLayout.setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View view){
        UriHandler.open(StringUtils.formatUs("https://www.douban.com/review/%d",review.id),context);
      }
    }
);
    ++i;
  }
  ViewUtils.setVisibleOrGone(mReviewList,i != 0);
  ViewUtils.setVisibleOrGone(mEmptyView,i == 0);
  if (reviewList.size() > i) {
    mViewMoreText.setText(R.string.view_more);
  }
 else {
    mViewMoreText.setVisibility(GONE);
  }
  for (int count=mReviewList.getChildCount(); i < count; ++i) {
    ViewUtils.setVisibleOrGone(mReviewList.getChildAt(i),false);
  }
}
