@Override public boolean isViewFromObject(View view,Object object){
  Router router=(Router)object;
  final List<RouterTransaction> backstack=router.getBackstack();
  for (  RouterTransaction transaction : backstack) {
    if (transaction.controller().getView() == view) {
      return true;
    }
  }
  return false;
}
