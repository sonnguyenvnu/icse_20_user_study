public void onNameClick(int index,View widget,User user){
  if (listener != null) {
    listener.onNameClick(index,widget,user);
  }
 else {
    CommonUtil.toActivity((Activity)getContext(),UserActivity.createIntent(getContext(),user.getId()));
  }
}
