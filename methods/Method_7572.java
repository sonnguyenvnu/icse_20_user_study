private void presentFragmentInternalRemoveOld(boolean removeLast,final BaseFragment fragment){
  if (fragment == null) {
    return;
  }
  fragment.onBecomeFullyHidden();
  fragment.onPause();
  if (removeLast) {
    fragment.onFragmentDestroy();
    fragment.setParentLayout(null);
    fragmentsStack.remove(fragment);
  }
 else {
    if (fragment.fragmentView != null) {
      ViewGroup parent=(ViewGroup)fragment.fragmentView.getParent();
      if (parent != null) {
        fragment.onRemoveFromParent();
        parent.removeView(fragment.fragmentView);
      }
    }
    if (fragment.actionBar != null && fragment.actionBar.getAddToContainer()) {
      ViewGroup parent=(ViewGroup)fragment.actionBar.getParent();
      if (parent != null) {
        parent.removeView(fragment.actionBar);
      }
    }
  }
  containerViewBack.setVisibility(View.GONE);
}
