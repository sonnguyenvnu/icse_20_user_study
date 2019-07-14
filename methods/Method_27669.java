@OnClick(R.id.fab) public void onViewClicked(){
  ActivityHelper.startReveal(this,new Intent(this,CreateGistActivity.class),fab,BundleConstant.REQUEST_CODE);
}
