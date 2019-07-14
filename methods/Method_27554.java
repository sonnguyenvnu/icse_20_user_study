@OnClick(R.id.select) public void onSelectClicked(){
  if (ActivityHelper.checkAndRequestReadWritePermission(getActivity())) {
    Intent intent=new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent,getString(R.string.select_picture)),BundleConstant.REQUEST_CODE);
  }
}
