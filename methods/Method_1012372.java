private void hide(final View view){
  if (view.getTranslationY() != view.getHeight()) {
    ObjectAnimator.ofFloat(view,"translationY",view.getTranslationY(),view.getHeight()).setDuration(200).start();
  }
}
