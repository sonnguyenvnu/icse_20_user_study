@OnClick(R.id.button_normal_dialog) void onNormalDialogClick(){
  new SimpleDialogFragment().show(getSupportFragmentManager(),"test-normal");
}
