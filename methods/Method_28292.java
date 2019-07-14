@Override public void onReplyOrCreateReview(@Nullable User user,@Nullable String message,int groupPosition,int childPosition,@NonNull EditReviewCommentModel model){
  Intent intent=new Intent(getContext(),EditorActivity.class);
  if (getPullRequest() == null)   return;
  intent.putExtras(Bundler.start().put(BundleConstant.ID,getPullRequest().getRepoId()).put(BundleConstant.EXTRA_TWO,getPullRequest().getLogin()).put(BundleConstant.EXTRA_THREE,getPullRequest().getNumber()).put(BundleConstant.EXTRA,user != null ? "@" + user.getLogin() : "").put(BundleConstant.REVIEW_EXTRA,model).put(BundleConstant.EXTRA_TYPE,BundleConstant.ExtraType.NEW_REVIEW_COMMENT_EXTRA).putStringArrayList("participants",CommentsHelper.getUsersByTimeline(adapter.getData())).put(BundleConstant.IS_ENTERPRISE,isEnterprise()).put("message",message).end());
  View view=getFromView();
  ActivityHelper.startReveal(this,intent,view,BundleConstant.REVIEW_REQUEST_CODE);
}
