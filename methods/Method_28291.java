@Override public void onReply(User user,String message){
  Intent intent=new Intent(getContext(),EditorActivity.class);
  if (getPullRequest() == null)   return;
  intent.putExtras(Bundler.start().put(BundleConstant.ID,getPullRequest().getRepoId()).put(BundleConstant.EXTRA_TWO,getPullRequest().getLogin()).put(BundleConstant.EXTRA_THREE,getPullRequest().getNumber()).put(BundleConstant.EXTRA,"@" + user.getLogin()).put(BundleConstant.EXTRA_TYPE,BundleConstant.ExtraType.NEW_ISSUE_COMMENT_EXTRA).putStringArrayList("participants",CommentsHelper.getUsersByTimeline(adapter.getData())).put(BundleConstant.IS_ENTERPRISE,isEnterprise()).put("message",message).end());
  View view=getFromView();
  ActivityHelper.startReveal(this,intent,view,BundleConstant.REQUEST_CODE);
}
