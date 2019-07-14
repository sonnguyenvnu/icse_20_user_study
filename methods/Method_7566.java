public void dismissDialogs(){
  if (!fragmentsStack.isEmpty()) {
    BaseFragment lastFragment=fragmentsStack.get(fragmentsStack.size() - 1);
    lastFragment.dismissCurrentDialig();
  }
}
