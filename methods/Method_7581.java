private void removeFragmentFromStackInternal(BaseFragment fragment){
  fragment.onPause();
  fragment.onFragmentDestroy();
  fragment.setParentLayout(null);
  fragmentsStack.remove(fragment);
}
