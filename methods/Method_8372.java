@Override protected void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen && !backward && needOpenSearch) {
    searchItem.openSearch(true);
  }
}
