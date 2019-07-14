private void showTosActivity(int account,TLRPC.TL_help_termsOfService tos){
  if (termsOfServiceView == null) {
    termsOfServiceView=new TermsOfServiceView(this);
    drawerLayoutContainer.addView(termsOfServiceView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
    termsOfServiceView.setDelegate(new TermsOfServiceView.TermsOfServiceViewDelegate(){
      @Override public void onAcceptTerms(      int account){
        UserConfig.getInstance(account).unacceptedTermsOfService=null;
        UserConfig.getInstance(account).saveConfig(false);
        drawerLayoutContainer.setAllowOpenDrawer(true,false);
        termsOfServiceView.setVisibility(View.GONE);
      }
      @Override public void onDeclineTerms(      int account){
        drawerLayoutContainer.setAllowOpenDrawer(true,false);
        termsOfServiceView.setVisibility(View.GONE);
      }
    }
);
  }
  TLRPC.TL_help_termsOfService currentTos=UserConfig.getInstance(account).unacceptedTermsOfService;
  if (currentTos != tos && (currentTos == null || !currentTos.id.data.equals(tos.id.data))) {
    UserConfig.getInstance(account).unacceptedTermsOfService=tos;
    UserConfig.getInstance(account).saveConfig(false);
  }
  termsOfServiceView.show(account,tos);
  drawerLayoutContainer.setAllowOpenDrawer(false,false);
}
