@Override public void onReply(User user,String message){
  if (getIssue() == null)   return;
  Intent intent=new Intent(getContext(),EditorActivity.class);
  intent.putExtras(Bundler.start().put(BundleConstant.ID,getIssue().getRepoId()).put(BundleConstant.EXTRA_TWO,getIssue().getLogin()).put(BundleConstant.EXTRA_THREE,getIssue().getNumber()).put(BundleConstant.EXTRA,"@" + user.getLogin()).put(BundleConstant.EXTRA_TYPE,BundleConstant.ExtraType.NEW_ISSUE_COMMENT_EXTRA).putStringArrayList("participants",CommentsHelper.getUsersByTimeline(adapter.getData())).put(BundleConstant.IS_ENTERPRISE,isEnterprise()).put("message",message).end());
  View view=getActivity() != null && getActivity().findViewById(R.id.fab) != null ? getActivity().findViewById(R.id.fab) : recycler;
  ActivityHelper.startReveal(this,intent,view,BundleConstant.REQUEST_CODE);
}
