private void showUpdateActivity(int account,TLRPC.TL_help_appUpdate update){
  if (blockingUpdateView == null) {
    blockingUpdateView=new BlockingUpdateView(LaunchActivity.this){
      @Override public void setVisibility(      int visibility){
        super.setVisibility(visibility);
        if (visibility == View.GONE) {
          drawerLayoutContainer.setAllowOpenDrawer(true,false);
        }
      }
    }
;
    drawerLayoutContainer.addView(blockingUpdateView,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  }
  blockingUpdateView.show(account,update);
  drawerLayoutContainer.setAllowOpenDrawer(false,false);
}
