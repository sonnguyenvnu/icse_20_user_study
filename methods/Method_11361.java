/** 
 * Converts source rectangle from tile, which treats the image file as if it were in the correct orientation already, to the rectangle of the image that needs to be loaded.
 */
@SuppressWarnings("SuspiciousNameCombination") @AnyThread private void fileSRect(Rect sRect,Rect target){
  if (getRequiredRotation() == 0) {
    target.set(sRect);
  }
 else   if (getRequiredRotation() == 90) {
    target.set(sRect.top,sHeight - sRect.right,sRect.bottom,sHeight - sRect.left);
  }
 else   if (getRequiredRotation() == 180) {
    target.set(sWidth - sRect.right,sHeight - sRect.bottom,sWidth - sRect.left,sHeight - sRect.top);
  }
 else {
    target.set(sWidth - sRect.bottom,sRect.left,sWidth - sRect.top,sRect.right);
  }
}
