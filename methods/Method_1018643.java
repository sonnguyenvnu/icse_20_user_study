/** 
 * raise the labelText labelText Label when gaining focus
 */
protected void activate(boolean animated){
  this.editText.setAlpha(1);
  if (this.editText.getText().toString().isEmpty() && !isActivated()) {
    this.editTextLayout.setAlpha(0f);
    this.floatingLabel.setScaleX(1f);
    this.floatingLabel.setScaleY(1f);
    this.floatingLabel.setTranslationY(0);
  }
  final boolean keepHint=this.alwaysShowHint && !this.editText.getHint().toString().isEmpty();
  if (animated && !keepHint) {
    ViewCompat.animate(this.editTextLayout).alpha(1f).setDuration(ANIMATION_DURATION);
    ViewCompat.animate(this.floatingLabel).scaleX(0.75f).scaleY(0.75f).translationY(-labelTopMargin + getContext().getResources().getDimensionPixelOffset(R.dimen.label_active_margin_top)).setDuration(ANIMATION_DURATION);
  }
 else {
    this.editTextLayout.setAlpha(1f);
    this.floatingLabel.setScaleX(0.75f);
    this.floatingLabel.setScaleY(0.75f);
    this.floatingLabel.setTranslationY(-labelTopMargin + getContext().getResources().getDimensionPixelOffset(R.dimen.label_active_margin_top));
  }
  activated=true;
}
