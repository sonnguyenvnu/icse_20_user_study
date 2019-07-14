@OnClick(R.id.facebook_login_button) public void facebookLoginClick(){
  this.viewModel.inputs.facebookLoginClick(this,Arrays.asList(getResources().getStringArray(R.array.facebook_permissions_array)));
}
