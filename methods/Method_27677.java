@OnClick(R.id.browserLogin) void onOpenBrowser(){
  if (isEnterprise()) {
    MessageDialogView.newInstance(getString(R.string.warning),getString(R.string.github_enterprise_reply),true,Bundler.start().put("hide_buttons",true).end()).show(getSupportFragmentManager(),MessageDialogView.TAG);
    return;
  }
  ActivityHelper.startCustomTab(this,getPresenter().getAuthorizationUrl());
}
