void applyEdgeEffectColor(EdgeEffect edgeEffect){
  if (edgeEffect != null && Build.VERSION.SDK_INT >= 21 && glowColor != 0) {
    edgeEffect.setColor(glowColor);
  }
}
