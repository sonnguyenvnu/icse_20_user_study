@Override public void copyInto(InternalNode target){
  if (target == NULL_LAYOUT) {
    return;
  }
  if (mNodeInfo != null) {
    if (target.getNodeInfo() == null) {
      target.setNodeInfo(mNodeInfo);
    }
 else {
      mNodeInfo.copyInto(target.getOrCreateNodeInfo());
    }
  }
  if (target.isLayoutDirectionInherit()) {
    target.layoutDirection(getResolvedLayoutDirection());
  }
  if (target.isImportantForAccessibilityIsSet()) {
    target.importantForAccessibility(mImportantForAccessibility);
  }
  if ((mPrivateFlags & PFLAG_DUPLICATE_PARENT_STATE_IS_SET) != 0L) {
    target.duplicateParentState(mDuplicateParentState);
  }
  if ((mPrivateFlags & PFLAG_BACKGROUND_IS_SET) != 0L) {
    target.background(mBackground);
  }
  if ((mPrivateFlags & PFLAG_FOREGROUND_IS_SET) != 0L) {
    target.foreground(mForeground);
  }
  if (mForceViewWrapping) {
    target.wrapInView();
  }
  if ((mPrivateFlags & PFLAG_VISIBLE_HANDLER_IS_SET) != 0L) {
    target.visibleHandler(mVisibleHandler);
  }
  if ((mPrivateFlags & PFLAG_FOCUSED_HANDLER_IS_SET) != 0L) {
    target.focusedHandler(mFocusedHandler);
  }
  if ((mPrivateFlags & PFLAG_FULL_IMPRESSION_HANDLER_IS_SET) != 0L) {
    target.fullImpressionHandler(mFullImpressionHandler);
  }
  if ((mPrivateFlags & PFLAG_INVISIBLE_HANDLER_IS_SET) != 0L) {
    target.invisibleHandler(mInvisibleHandler);
  }
  if ((mPrivateFlags & PFLAG_UNFOCUSED_HANDLER_IS_SET) != 0L) {
    target.unfocusedHandler(mUnfocusedHandler);
  }
  if ((mPrivateFlags & PFLAG_VISIBLE_RECT_CHANGED_HANDLER_IS_SET) != 0L) {
    target.visibilityChangedHandler(mVisibilityChangedHandler);
  }
  if (mTestKey != null) {
    target.testKey(mTestKey);
  }
  if ((mPrivateFlags & PFLAG_PADDING_IS_SET) != 0L) {
    if (mNestedTreeProps == null || mNestedTreeProps.mNestedTreePadding == null) {
      throw new IllegalStateException("copyInto() must be used when resolving a nestedTree. If padding was set on the holder node, we must have a mNestedTreePadding instance");
    }
    for (int i=0; i < Edges.EDGES_LENGTH; i++) {
      float value=mNestedTreeProps.mNestedTreePadding.getRaw(i);
      if (!YogaConstants.isUndefined(value)) {
        final YogaEdge edge=YogaEdge.fromInt(i);
        if (isPaddingPercent(edge)) {
          target.paddingPercent(edge,value);
        }
 else {
          target.paddingPx(edge,(int)value);
        }
      }
    }
  }
  if ((mPrivateFlags & PFLAG_BORDER_IS_SET) != 0L) {
    if (mNestedTreeProps == null || mNestedTreeProps.mNestedTreeBorderWidth == null) {
      throw new IllegalStateException("copyInto() must be used when resolving a nestedTree.If border width was set on the holder node, we must have a mNestedTreeBorderWidth instance");
    }
    target.border(mNestedTreeProps.mNestedTreeBorderWidth,mBorderColors,mBorderRadius);
  }
  if ((mPrivateFlags & PFLAG_TRANSITION_KEY_IS_SET) != 0L) {
    target.transitionKey(mTransitionKey);
  }
  if ((mPrivateFlags & PFLAG_TRANSITION_KEY_TYPE_IS_SET) != 0L) {
    target.transitionKeyType(mTransitionKeyType);
  }
  if (mVisibleHeightRatio != 0) {
    target.visibleHeightRatio(mVisibleHeightRatio);
  }
  if (mVisibleWidthRatio != 0) {
    target.visibleWidthRatio(mVisibleWidthRatio);
  }
  if ((mPrivateFlags & PFLAG_STATE_LIST_ANIMATOR_SET) != 0L) {
    target.stateListAnimator(mStateListAnimator);
  }
  if ((mPrivateFlags & PFLAG_STATE_LIST_ANIMATOR_RES_SET) != 0L) {
    target.stateListAnimatorRes(mStateListAnimatorRes);
  }
}
