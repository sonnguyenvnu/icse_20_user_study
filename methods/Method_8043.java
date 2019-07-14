public void setEnabled(boolean value,ArrayList<Animator> animators){
  if (animators != null) {
    animators.add(ObjectAnimator.ofFloat(textView,"alpha",value ? 1.0f : 0.5f));
  }
 else {
    textView.setAlpha(value ? 1.0f : 0.5f);
  }
}
