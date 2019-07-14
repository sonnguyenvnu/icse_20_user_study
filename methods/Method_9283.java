private void switchToAvailableAccountOrLogout(){
  int account=-1;
  for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
    if (UserConfig.getInstance(a).isClientActivated()) {
      account=a;
      break;
    }
  }
  if (termsOfServiceView != null) {
    termsOfServiceView.setVisibility(View.GONE);
  }
  if (account != -1) {
    switchToAccount(account,true);
  }
 else {
    if (drawerLayoutAdapter != null) {
      drawerLayoutAdapter.notifyDataSetChanged();
    }
    for (    BaseFragment fragment : actionBarLayout.fragmentsStack) {
      fragment.onFragmentDestroy();
    }
    actionBarLayout.fragmentsStack.clear();
    if (AndroidUtilities.isTablet()) {
      for (      BaseFragment fragment : layersActionBarLayout.fragmentsStack) {
        fragment.onFragmentDestroy();
      }
      layersActionBarLayout.fragmentsStack.clear();
      for (      BaseFragment fragment : rightActionBarLayout.fragmentsStack) {
        fragment.onFragmentDestroy();
      }
      rightActionBarLayout.fragmentsStack.clear();
    }
    Intent intent2=new Intent(this,IntroActivity.class);
    startActivity(intent2);
    onFinish();
    finish();
  }
}
