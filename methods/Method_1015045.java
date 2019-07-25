@OnClick(R.id.aliasOptionItemView) void alias(){
  String selfUid=userViewModel.getUserId();
  if (selfUid.equals(userInfo.uid)) {
    Intent intent=new Intent(getActivity(),ChangeMyNameActivity.class);
    startActivity(intent);
  }
 else {
    Intent intent=new Intent(getActivity(),SetAliasActivity.class);
    intent.putExtra("userId",userInfo.uid);
    startActivity(intent);
  }
}
