public BaseFragment getFragmentForAlert(int offset){
  if (parentLayout == null || parentLayout.fragmentsStack.size() <= 1 + offset) {
    return this;
  }
  return parentLayout.fragmentsStack.get(parentLayout.fragmentsStack.size() - 2 - offset);
}
