public void show(PermissionRequest request){
  mRequest=request;
  setMessage();
  mRemember.setChecked(true);
  setVisibility(View.VISIBLE);
}
