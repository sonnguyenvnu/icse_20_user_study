private void show(final View view){
  if (view.getTranslationY() != 0) {
    ObjectAnimator.ofFloat(view,"translationY",view.getTranslationY(),0).setDuration(200).start();
  }
}
