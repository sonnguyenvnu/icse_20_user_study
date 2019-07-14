public void visibilityChanged(@FloatRange(from=0.0f,to=100.0f) float percentVisibleHeight,@FloatRange(from=0.0f,to=100.0f) float percentVisibleWidth,@Px int visibleHeight,@Px int visibleWidth){
  assertBound();
  epoxyModel.onVisibilityChanged(percentVisibleHeight,percentVisibleWidth,visibleHeight,visibleWidth,objectToBind());
}
