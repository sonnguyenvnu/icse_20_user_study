public void finishPreviewFragment(){
  if (!inPreviewMode && !transitionAnimationPreviewMode) {
    return;
  }
  closeLastFragment(true);
}
