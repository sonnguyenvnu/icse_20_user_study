@Override public void onEditReviewComment(@NonNull ReviewCommentModel item,int groupPosition,int childPosition){
  EditReviewCommentModel model=new EditReviewCommentModel();
  model.setCommentPosition(childPosition);
  model.setGroupPosition(groupPosition);
  model.setInReplyTo(item.getId());
  Intent intent=new Intent(getContext(),EditorActivity.class);
  if (getPullRequest() == null)   return;
  intent.putExtras(Bundler.start().put(BundleConstant.ID,getPullRequest().getRepoId()).put(BundleConstant.EXTRA_TWO,getPullRequest().getLogin()).put(BundleConstant.EXTRA_THREE,getPullRequest().getNumber()).put(BundleConstant.EXTRA_FOUR,item.getId()).put(BundleConstant.EXTRA,item.getBody()).put(BundleConstant.REVIEW_EXTRA,model).put(BundleConstant.EXTRA_TYPE,BundleConstant.ExtraType.EDIT_REVIEW_COMMENT_EXTRA).putStringArrayList("participants",CommentsHelper.getUsersByTimeline(adapter.getData())).put(BundleConstant.IS_ENTERPRISE,isEnterprise()).end());
  View view=getFromView();
  ActivityHelper.startReveal(this,intent,view,BundleConstant.REVIEW_REQUEST_CODE);
}
