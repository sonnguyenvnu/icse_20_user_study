public void visibilityStateChanged(@Visibility int visibilityState){
  assertBound();
  epoxyModel.onVisibilityStateChanged(visibilityState,objectToBind());
}
