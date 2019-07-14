private void updateTouchAreas(){
  int touchPadding=AndroidUtilities.dp(16);
  topLeftCorner.set(actualRect.left - touchPadding,actualRect.top - touchPadding,actualRect.left + touchPadding,actualRect.top + touchPadding);
  topRightCorner.set(actualRect.right - touchPadding,actualRect.top - touchPadding,actualRect.right + touchPadding,actualRect.top + touchPadding);
  bottomLeftCorner.set(actualRect.left - touchPadding,actualRect.bottom - touchPadding,actualRect.left + touchPadding,actualRect.bottom + touchPadding);
  bottomRightCorner.set(actualRect.right - touchPadding,actualRect.bottom - touchPadding,actualRect.right + touchPadding,actualRect.bottom + touchPadding);
  topEdge.set(actualRect.left + touchPadding,actualRect.top - touchPadding,actualRect.right - touchPadding,actualRect.top + touchPadding);
  leftEdge.set(actualRect.left - touchPadding,actualRect.top + touchPadding,actualRect.left + touchPadding,actualRect.bottom - touchPadding);
  rightEdge.set(actualRect.right - touchPadding,actualRect.top + touchPadding,actualRect.right + touchPadding,actualRect.bottom - touchPadding);
  bottomEdge.set(actualRect.left + touchPadding,actualRect.bottom - touchPadding,actualRect.right - touchPadding,actualRect.bottom + touchPadding);
}
