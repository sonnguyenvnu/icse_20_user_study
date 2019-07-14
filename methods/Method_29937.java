private void bind(Broadcast broadcast,Broadcast rebroadcastedBroadcast,boolean isSimpleRebroadcastByOneself,boolean isUnrebroadcasting){
  Context context=getContext();
  ImageUtils.loadAvatar(mAvatarImage,broadcast.author.avatar);
  mAvatarImage.setOnClickListener(view -> context.startActivity(ProfileActivity.makeIntent(broadcast.author,context)));
  mNameText.setText(broadcast.author.name);
  boolean hasTime=!TextUtils.isEmpty(broadcast.createTime);
  ViewUtils.setVisibleOrGone(mTimeText,hasTime);
  if (hasTime) {
    mTimeText.setDoubanTime(broadcast.createTime);
  }
  ViewUtils.setVisibleOrGone(mTimeActionSpace,hasTime);
  mActionText.setText(broadcast.action);
  boolean isRebind=ObjectsCompat.equals(mBoundBroadcastId,broadcast.id);
  boolean hasParentBroadcast=broadcast.parentBroadcast != null;
  if (!(isRebind && ObjectsCompat.equals(mBoundBroadcastHadParentBroadcast,hasParentBroadcast))) {
    mBoundBroadcastHadParentBroadcast=hasParentBroadcast;
    mTextText.setText(broadcast.getTextWithEntities(mTextText.getContext()));
  }
  boolean hasRebroadcastedBroadcast=rebroadcastedBroadcast != null;
  if (!(isRebind && (!hasRebroadcastedBroadcast || ObjectsCompat.equals(mBoundBroadcastRebroadcastedBroadcastWasDeleted,rebroadcastedBroadcast.isDeleted)))) {
    if (hasRebroadcastedBroadcast) {
      mBoundBroadcastRebroadcastedBroadcastWasDeleted=rebroadcastedBroadcast.isDeleted;
    }
    bindRebroadcastedAttachmentImages(broadcast,rebroadcastedBroadcast);
  }
 else   if (hasRebroadcastedBroadcast) {
    setRebroadcastedAttachmentImagesLayoutOnClickListener(rebroadcastedBroadcast);
  }
  mLikeButton.setText(broadcast.getLikeCountString());
  LikeBroadcastManager likeBroadcastManager=LikeBroadcastManager.getInstance();
  if (likeBroadcastManager.isWriting(broadcast.id)) {
    mLikeButton.setActivated(likeBroadcastManager.isWritingLike(broadcast.id));
    mLikeButton.setEnabled(false);
  }
 else {
    mLikeButton.setActivated(broadcast.isLiked);
    mLikeButton.setEnabled(true);
  }
  mLikeButton.setOnClickListener(view -> {
    if (mListener == null) {
      return;
    }
    mListener.onLikeClicked();
  }
);
  mRebroadcastButton.setText(broadcast.getRebroadcastCountString());
  if (isSimpleRebroadcastByOneself) {
    mRebroadcastButton.setActivated(!isUnrebroadcasting);
    mRebroadcastButton.setEnabled(!isUnrebroadcasting);
  }
 else {
    boolean isWritingQuickRebroadcast=RebroadcastBroadcastManager.getInstance().isWritingQuickRebroadcast(broadcast.id);
    mRebroadcastButton.setActivated(isWritingQuickRebroadcast);
    mRebroadcastButton.setEnabled(!isWritingQuickRebroadcast);
  }
  mRebroadcastButton.setOnClickListener(view -> {
    if (mListener == null) {
      return;
    }
    mListener.onRebroadcastClicked(false);
  }
);
  TooltipUtils.setup(mRebroadcastButton);
  View.OnLongClickListener rebroadcastTooltipListener=mRebroadcastButton.getOnLongClickListener();
  mRebroadcastButton.setOnLongClickListener(view -> {
    if (mListener == null || !Settings.LONG_CLICK_TO_QUICK_REBROADCAST.getValue()) {
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        return rebroadcastTooltipListener.onLongClick(view);
      }
 else {
        return true;
      }
    }
    mListener.onRebroadcastClicked(true);
    return true;
  }
);
  mCommentButton.setText(broadcast.getCommentCountString());
  mCommentButton.setOnClickListener(view -> {
    if (mListener != null) {
      mListener.onCommentClicked();
    }
  }
);
  mBoundBroadcastId=broadcast.id;
}
