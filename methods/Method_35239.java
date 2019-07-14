@Override public void setPrimaryItem(ViewGroup container,int position,Object object){
  Router router=(Router)object;
  if (router != currentPrimaryRouter) {
    if (currentPrimaryRouter != null) {
      for (      RouterTransaction transaction : currentPrimaryRouter.getBackstack()) {
        transaction.controller().setOptionsMenuHidden(true);
      }
    }
    if (router != null) {
      for (      RouterTransaction transaction : router.getBackstack()) {
        transaction.controller().setOptionsMenuHidden(false);
      }
    }
    currentPrimaryRouter=router;
  }
}
