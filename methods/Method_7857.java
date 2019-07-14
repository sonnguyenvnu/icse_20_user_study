private void openPreviewsChat(TLRPC.User user,long wid){
  if (user == null || parentActivity == null) {
    return;
  }
  Bundle args=new Bundle();
  args.putInt("user_id",user.id);
  args.putString("botUser","webpage" + wid);
  ((LaunchActivity)parentActivity).presentFragment(new ChatActivity(args),false,true);
  close(false,true);
}
