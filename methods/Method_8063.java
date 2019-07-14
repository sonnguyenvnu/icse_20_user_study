public void setEnabled(boolean value,ArrayList<Animator> animators){
  super.setEnabled(value);
  if (animators != null) {
    animators.add(ObjectAnimator.ofFloat(textView,"alpha",value ? 1.0f : 0.5f));
    animators.add(ObjectAnimator.ofFloat(seekBarView,"alpha",value ? 1.0f : 0.5f));
    animators.add(ObjectAnimator.ofFloat(sizeTextView,"alpha",value ? 1.0f : 0.5f));
  }
 else {
    textView.setAlpha(value ? 1.0f : 0.5f);
    seekBarView.setAlpha(value ? 1.0f : 0.5f);
    sizeTextView.setAlpha(value ? 1.0f : 0.5f);
  }
}
