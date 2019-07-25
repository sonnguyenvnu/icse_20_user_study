@Override public void bind(TimelineItem.TimelineReview item){
  Review review=item.review();
  mShowDetailsButton.setTag(review);
  AvatarHandler.assignAvatar(mAvatarView,review.user());
  mAvatarContainer.setTag(review.user());
  formatTitle(review);
  boolean hasBody=!TextUtils.isEmpty(review.body());
  if (hasBody) {
    mImageGetter.bind(mBodyView,review.bodyHtml(),review.id());
    mBodyView.setVisibility(View.VISIBLE);
  }
 else {
    mBodyView.setVisibility(View.GONE);
  }
  if (mCallback.canQuote()) {
    mBodyView.setCustomSelectionActionModeCallback(mQuoteActionModeCallback);
  }
 else {
    mBodyView.setCustomSelectionActionModeCallback(null);
  }
  boolean hasDiffs=!item.getDiffHunks().isEmpty();
  if (mDisplayReviewDetails && hasDiffs) {
    LayoutInflater inflater=LayoutInflater.from(mContext);
    Map<String,FileDetails> files=new HashMap<>();
    int viewIndex=0;
    for (    TimelineItem.Diff diffHunk : item.getDiffHunks()) {
      ReviewComment commitComment=diffHunk.getInitialComment();
      String filename=commitComment.path();
      int commentCount=diffHunk.comments.size();
      boolean isOutdated=commitComment.position() == null;
      if (files.containsKey(filename)) {
        FileDetails details=files.get(filename);
        details.isOutdated=details.isOutdated && isOutdated;
        details.count+=commentCount;
        continue;
      }
      View row=mDetailsContainer.getChildAt(viewIndex);
      if (row == null) {
        row=inflater.inflate(R.layout.row_timeline_review_file_details,mDetailsContainer,false);
        mDetailsContainer.addView(row);
        row.setOnClickListener(this);
      }
      row.setTag(review);
      row.setTag(R.id.review_comment_id,commitComment.id());
      files.put(filename,new FileDetails(row,isOutdated,commentCount));
      viewIndex+=1;
    }
    for (    Map.Entry<String,FileDetails> detailsEntry : files.entrySet()) {
      FileDetails fileDetails=detailsEntry.getValue();
      TextView tvFile=fileDetails.row.findViewById(R.id.tv_file);
      tvFile.setText("• " + detailsEntry.getKey());
      if (fileDetails.isOutdated) {
        tvFile.setPaintFlags(tvFile.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
      }
 else {
        tvFile.setPaintFlags(tvFile.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
      }
      TextView tvFileComments=fileDetails.row.findViewById(R.id.tv_file_comments);
      tvFileComments.setText(String.valueOf(fileDetails.count));
      fileDetails.row.setVisibility(View.VISIBLE);
    }
    for (int i=viewIndex; i < mDetailsContainer.getChildCount(); i++) {
      mDetailsContainer.getChildAt(i).setVisibility(View.GONE);
    }
    mDetailsContainer.setVisibility(View.VISIBLE);
    mShowDetailsButton.setVisibility(View.VISIBLE);
    mDetailsHeader.setVisibility(View.VISIBLE);
  }
 else {
    mDetailsContainer.setVisibility(View.GONE);
    mShowDetailsButton.setVisibility(View.GONE);
    mDetailsHeader.setVisibility(View.GONE);
  }
  if (hasBody && mDisplayReviewDetails && hasDiffs) {
    mDetailsDivider.setVisibility(View.VISIBLE);
  }
 else {
    mDetailsDivider.setVisibility(View.GONE);
  }
  ivMenu.setVisibility(mDisplayReviewDetails ? View.VISIBLE : View.GONE);
  ivMenu.setTag(review);
  mEventIconView.setImageResource(getEventIconResId(review));
}
