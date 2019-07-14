private void removeAllExceptVisibleAndUnowned(){
  List<View> views=new ArrayList<>();
  for (  RouterTransaction transaction : getVisibleTransactions(backstack.iterator())) {
    if (transaction.controller.getView() != null) {
      views.add(transaction.controller.getView());
    }
  }
  for (  Router router : getSiblingRouters()) {
    if (router.container == container) {
      addRouterViewsToList(router,views);
    }
  }
  final int childCount=container.getChildCount();
  for (int i=childCount - 1; i >= 0; i--) {
    final View child=container.getChildAt(i);
    if (!views.contains(child)) {
      container.removeView(child);
    }
  }
}
