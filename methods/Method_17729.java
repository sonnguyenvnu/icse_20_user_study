private void onBindingFinished(AnimationBinding binding){
  if (binding != mBindings.get(mCurrentIndex)) {
    throw new RuntimeException("Unexpected Binding completed");
  }
  binding.removeListener(mChildListener);
  mCurrentIndex++;
  if (mCurrentIndex >= mBindings.size()) {
    finish();
  }
 else {
    AnimationBinding next=mBindings.get(mCurrentIndex);
    next.addListener(mChildListener);
    next.start(mResolver);
  }
}
