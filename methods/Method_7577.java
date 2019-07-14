private void closeLastFragmentInternalRemoveOld(BaseFragment fragment){
  fragment.onPause();
  fragment.onFragmentDestroy();
  fragment.setParentLayout(null);
  fragmentsStack.remove(fragment);
  containerViewBack.setVisibility(View.GONE);
  bringChildToFront(containerView);
}
