private void onUriOverrideClicked(){
  if (mSetUriOverrideDialog == null) {
    mSetUriOverrideDialog=new UriOverrideDialog();
  }
  mSetUriOverrideDialog.show(getFragmentManager(),"uri_override");
}
