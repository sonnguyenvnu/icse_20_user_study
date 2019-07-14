public float getHightlightAlpha(){
  if (!drawSelectionBackground && isHighlightedAnimated) {
    return highlightProgress >= 300 ? 1.0f : highlightProgress / 300.0f;
  }
  return 1.0f;
}
