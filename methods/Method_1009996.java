@Override public void bind(TimelineItem.Reply item){
  boolean selected=item.timelineComment.comment().id() == mCallback.getSelectedCommentId();
  mReplyButton.setTag(item.timelineComment);
  mReplyButton.setText(selected ? R.string.reply_selected : R.string.reply);
}
