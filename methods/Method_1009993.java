@Override public void bind(TimelineItem.TimelineComment item){
  mBoundItem=item;
  User user=item.getUser();
  Date createdAt=item.getCreatedAt();
  Date updatedAt=item.comment().updatedAt();
  tvExtra.setTag(user);
  AvatarHandler.assignAvatar(ivGravatar,user);
  ivGravatar.setTag(user);
  tvTimestamp.setText(StringUtils.formatRelativeTime(mContext,createdAt,true));
  if (createdAt.equals(updatedAt) || item.getReviewComment() != null) {
    tvEditTimestamp.setVisibility(View.GONE);
  }
 else {
    tvEditTimestamp.setText(StringUtils.formatRelativeTime(mContext,updatedAt,true));
    tvEditTimestamp.setVisibility(View.VISIBLE);
  }
  mImageGetter.bind(tvDesc,item.comment().bodyHtml(),item.comment().id());
  String login=ApiHelpers.getUserLogin(mContext,user);
  SpannableStringBuilder userName=new SpannableStringBuilder(login);
  userName.setSpan(new StyleSpan(Typeface.BOLD),0,userName.length(),0);
  String association=getAuthorAssociation(item);
  if (association != null) {
    int start=userName.length();
    userName.append(" (").append(association).append(")");
    userName.setSpan(new RelativeSizeSpan(0.85f),start,userName.length(),0);
    int color=UiUtils.resolveColor(mContext,android.R.attr.textColorSecondary);
    userName.setSpan(new ForegroundColorSpan(color),start,userName.length(),0);
  }
  tvExtra.setText(userName);
  if (mCallback.canQuote()) {
    tvDesc.setCustomSelectionActionModeCallback(mQuoteActionModeCallback);
  }
 else {
    tvDesc.setCustomSelectionActionModeCallback(null);
  }
  ivMenu.setTag(item);
  reactions.setReactions(item.comment().reactions());
  String ourLogin=Gh4Application.get().getAuthLogin();
  boolean canEdit=ApiHelpers.loginEquals(user,ourLogin) || ApiHelpers.loginEquals(mRepoOwner,ourLogin);
  int position=item.getReviewComment() != null && item.getReviewComment().position() != null ? item.getReviewComment().position() : -1;
  Menu menu=mPopupMenu.getMenu();
  menu.findItem(R.id.edit).setVisible(canEdit);
  menu.findItem(R.id.delete).setVisible(canEdit);
  menu.findItem(R.id.view_in_file).setVisible(item.file != null && position != -1);
}
