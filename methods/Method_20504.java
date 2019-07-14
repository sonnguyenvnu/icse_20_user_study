/** 
 * Convert source rect to screen rect, integer values.
 */
private void sourceToViewRect(@NonNull Rect sRect,@NonNull Rect vTarget){
  vTarget.set((int)sourceToViewX(sRect.left),(int)sourceToViewY(sRect.top),(int)sourceToViewX(sRect.right),(int)sourceToViewY(sRect.bottom));
}
