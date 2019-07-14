private void openForward(){
  Bundle args=new Bundle();
  args.putBoolean("onlySelect",true);
  args.putInt("dialogsType",3);
  DialogsActivity fragment=new DialogsActivity(args);
  fragment.setDelegate(ChatActivity.this);
  presentFragment(fragment);
}
