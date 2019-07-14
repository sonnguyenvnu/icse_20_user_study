public void onReorderStateChanged(boolean reordering,boolean animated){
  if (!drawPin && reordering || drawReorder == reordering) {
    if (!drawPin) {
      drawReorder=false;
    }
    return;
  }
  drawReorder=reordering;
  if (animated) {
    reorderIconProgress=drawReorder ? 0.0f : 1.0f;
  }
 else {
    reorderIconProgress=drawReorder ? 1.0f : 0.0f;
  }
  invalidate();
}
