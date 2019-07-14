public boolean hasNameLayout(){
  return drawNameLayout && nameLayout != null || drawForwardedName && forwardedNameLayout[0] != null && forwardedNameLayout[1] != null && (currentPosition == null || currentPosition.minY == 0 && currentPosition.minX == 0) || replyNameLayout != null;
}
