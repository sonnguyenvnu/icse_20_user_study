private void onBindingFinished(AnimationBinding binding){
  if (mBindingsFinished.contains(binding)) {
    throw new RuntimeException("Binding unexpectedly completed twice");
  }
  mBindingsFinished.add(binding);
  mChildrenFinished++;
  binding.removeListener(mChildListener);
  if (mChildrenFinished >= mBindings.size()) {
    finish();
  }
}
