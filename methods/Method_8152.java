public void setEnabled(boolean value,ArrayList<Animator> animators){
  setEnabled(value);
  if (animators != null) {
    animators.add(ObjectAnimator.ofFloat(textView,"alpha",value ? 1.0f : 0.5f));
    if (valueTextView.getVisibility() == VISIBLE) {
      animators.add(ObjectAnimator.ofFloat(valueTextView,"alpha",value ? 1.0f : 0.5f));
    }
    if (valueImageView.getVisibility() == VISIBLE) {
      animators.add(ObjectAnimator.ofFloat(valueImageView,"alpha",value ? 1.0f : 0.5f));
    }
  }
 else {
    textView.setAlpha(value ? 1.0f : 0.5f);
    if (valueTextView.getVisibility() == VISIBLE) {
      valueTextView.setAlpha(value ? 1.0f : 0.5f);
    }
    if (valueImageView.getVisibility() == VISIBLE) {
      valueImageView.setAlpha(value ? 1.0f : 0.5f);
    }
  }
}
