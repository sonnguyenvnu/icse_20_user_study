@Override public void onEditComment(@NonNull Comment item){
  Intent intent=new Intent(getContext(),EditorActivity.class);
  intent.putExtras(Bundler.start().put(BundleConstant.ID,gistId).put(BundleConstant.EXTRA,item.getBody()).put(BundleConstant.EXTRA_FOUR,item.getId()).put(BundleConstant.EXTRA_TYPE,EDIT_GIST_COMMENT_EXTRA).putStringArrayList("participants",CommentsHelper.getUsers(adapter.getData())).put(BundleConstant.IS_ENTERPRISE,isEnterprise()).end());
  View view=getActivity() != null && getActivity().findViewById(R.id.fab) != null ? getActivity().findViewById(R.id.fab) : recycler;
  ActivityHelper.startReveal(this,intent,view,BundleConstant.REQUEST_CODE);
}
