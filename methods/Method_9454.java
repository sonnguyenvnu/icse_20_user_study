@Override public void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen && searchItem != null) {
    AndroidUtilities.showKeyboard(searchItem.getSearchField());
  }
}
