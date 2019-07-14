static void maybeInvalidateAccessibilityState(MountItem mountItem){
  if (mountItem.isAccessible()) {
    mountItem.getHost().invalidateAccessibilityState();
  }
}
