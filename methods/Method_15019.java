@Override public void bindView(MomentItem data_){
  this.data=data_;
  llMomentViewContainer.setVisibility(data == null ? View.GONE : View.VISIBLE);
  if (data == null) {
    Log.w(TAG,"bindView data == null >> return;");
    return;
  }
  this.user=data.getUser();
  this.moment=data.getMoment();
  this.momentId=moment.getId();
  this.userId=moment.getUserId();
  this.isCurrentUser=APIJSONApplication.getInstance().isCurrentUser(moment.getUserId());
  this.status=data.getMyStatus();
  ImageLoaderUtil.loadImage(ivMomentViewHead,user.getHead());
  tvMomentViewName.setText(StringUtil.getTrimedString(user.getName()));
  tvMomentViewStatus.setText(StringUtil.getTrimedString(data.getStatusString()));
  tvMomentViewStatus.setVisibility(isCurrentUser ? View.VISIBLE : View.GONE);
  tvMomentViewContent.setVisibility(StringUtil.isNotEmpty(moment.getContent(),true) ? View.VISIBLE : View.GONE);
  tvMomentViewContent.setText(StringUtil.getTrimedString(moment.getContent()));
  tvMomentViewDate.setText(TimeUtil.getSmartDate(moment.getDate()));
  setPicture(moment.getPictureList());
  setPraise(data.getIsPraised(),data.getUserList());
  setComment(data.getCommentItemList());
  vMomentViewDivider.setVisibility(llMomentViewPraise.getVisibility() == View.VISIBLE && llMomentViewCommentContainer.getVisibility() == View.VISIBLE ? View.VISIBLE : View.GONE);
}
