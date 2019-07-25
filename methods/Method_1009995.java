@Override public void bind(TimelineItem.TimelineEvent item){
  User user=item.event.assigner() != null ? item.event.assigner() : item.event.actor();
  AvatarHandler.assignAvatar(mAvatarView,user);
  mAvatarContainer.setTag(user);
  Integer eventIconAttr=EVENT_ICONS.get(item.event.event());
  if (eventIconAttr != null) {
    mEventIconView.setImageResource(UiUtils.resolveDrawable(mContext,eventIconAttr));
    mEventIconView.setVisibility(View.VISIBLE);
  }
 else {
    mEventIconView.setVisibility(View.GONE);
  }
  mMessageView.setText(formatEvent(item.event,user,mMessageView.getTypefaceValue(),mIsPullRequest));
}
