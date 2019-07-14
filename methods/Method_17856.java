void unmountDisappearingItem(MountItem disappearingItem){
  ensureDisappearingItems();
  if (!mDisappearingItems.remove(disappearingItem)) {
    final TransitionId transitionId=disappearingItem.getTransitionId();
    throw new RuntimeException("Tried to remove non-existent disappearing item, transitionId: " + transitionId);
  }
  final Object content=disappearingItem.getContent();
  if (content instanceof Drawable) {
    unmountDrawable(disappearingItem);
  }
 else   if (content instanceof View) {
    unmountView((View)content);
  }
  maybeInvalidateAccessibilityState(disappearingItem);
}
