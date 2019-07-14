@Override public void copyInto(ComponentContext c,InternalNode node){
  if (node == NULL_LAYOUT) {
    return;
  }
  if (mIntProps != null) {
    c.applyStyle(node,mIntProps.get(INDEX_DefStyleAttr),mIntProps.get(INDEX_DefStyleRes));
  }
  if (mNodeInfo != null) {
    mNodeInfo.copyInto(node.getOrCreateNodeInfo());
  }
  if ((mPrivateFlags & PFLAG_BACKGROUND_IS_SET) != 0L) {
    node.background((ComparableDrawable)mObjectProps.get(INDEX_Background));
  }
  if ((mPrivateFlags & PFLAG_TEST_KEY_IS_SET) != 0L) {
    node.testKey((String)mObjectProps.get(INDEX_TestKey));
  }
  if ((mPrivateFlags & PFLAG_POSITION_TYPE_IS_SET) != 0L) {
    node.positionType((YogaPositionType)mObjectProps.get(INDEX_PositionType));
  }
  if ((mPrivateFlags & PFLAG_POSITION_IS_SET) != 0L) {
    Edges mPositions=(Edges)mObjectProps.get(INDEX_Positions);
    for (int i=0, length=YogaEdge.values().length; i < length; i++) {
      final YogaEdge edge=YogaEdge.fromInt(i);
      final float value=mPositions.getRaw(edge);
      if (!YogaConstants.isUndefined(value)) {
        node.positionPx(edge,(int)value);
      }
    }
  }
  if ((mPrivateFlags & PFLAG_WIDTH_IS_SET) != 0L) {
    node.widthPx(mIntProps.get(INDEX_WidthPx));
  }
  if ((mPrivateFlags & PFLAG_HEIGHT_IS_SET) != 0L) {
    node.heightPx(mIntProps.get(INDEX_HeightPx));
  }
  if ((mPrivateFlags & PFLAG_LAYOUT_DIRECTION_IS_SET) != 0L) {
    node.layoutDirection((YogaDirection)mObjectProps.get(INDEX_LayoutDirection));
  }
  if ((mPrivateFlags & PFLAG_IMPORTANT_FOR_ACCESSIBILITY_IS_SET) != 0L) {
    node.importantForAccessibility(mIntProps.get(INDEX_ImportantForAccessibility));
  }
  if ((mPrivateFlags & PFLAG_DUPLICATE_PARENT_STATE_IS_SET) != 0L) {
    node.duplicateParentState(mDuplicateParentState);
  }
  if ((mPrivateFlags & PFLAG_FOREGROUND_IS_SET) != 0L) {
    node.foreground((ComparableDrawable)mObjectProps.get(INDEX_Foreground));
  }
  if ((mPrivateFlags & PFLAG_WRAP_IN_VIEW_IS_SET) != 0L) {
    node.wrapInView();
  }
  if ((mPrivateFlags & PFLAG_VISIBLE_HANDLER_IS_SET) != 0L) {
    node.visibleHandler((EventHandler<VisibleEvent>)mObjectProps.get(INDEX_VisibleHandler));
  }
  if ((mPrivateFlags & PFLAG_FOCUSED_HANDLER_IS_SET) != 0L) {
    node.focusedHandler((EventHandler<FocusedVisibleEvent>)mObjectProps.get(INDEX_FocusedHandler));
  }
  if ((mPrivateFlags & PFLAG_FULL_IMPRESSION_HANDLER_IS_SET) != 0L) {
    node.fullImpressionHandler((EventHandler<FullImpressionVisibleEvent>)mObjectProps.get(INDEX_FullImpressionHandler));
  }
  if ((mPrivateFlags & PFLAG_INVISIBLE_HANDLER_IS_SET) != 0L) {
    node.invisibleHandler((EventHandler<InvisibleEvent>)mObjectProps.get(INDEX_InvisibleHandler));
  }
  if ((mPrivateFlags & PFLAG_UNFOCUSED_HANDLER_IS_SET) != 0L) {
    node.unfocusedHandler((EventHandler<UnfocusedVisibleEvent>)mObjectProps.get(INDEX_UnfocusedHandler));
  }
  if ((mPrivateFlags & PFLAG_VISIBILITY_CHANGED_HANDLER_IS_SET) != 0) {
    node.visibilityChangedHandler((EventHandler<VisibilityChangedEvent>)mObjectProps.get(INDEX_VisibilityChangedHandler));
  }
  if ((mPrivateFlags & PFLAG_TRANSITION_KEY_IS_SET) != 0L) {
    node.transitionKey((String)mObjectProps.get(INDEX_TransitionKey));
  }
  if ((mPrivateFlags & PFLAG_TRANSITION_KEY_TYPE_IS_SET) != 0L) {
    node.transitionKeyType((Transition.TransitionKeyType)mObjectProps.get(INDEX_TransitionKeyType));
  }
  if ((mPrivateFlags & PFLAG_VISIBLE_HEIGHT_RATIO_IS_SET) != 0L) {
    node.visibleHeightRatio(mFloatProps.get(INDEX_VisibleHeightRatio));
  }
  if ((mPrivateFlags & PFLAG_VISIBLE_WIDTH_RATIO_IS_SET) != 0L) {
    node.visibleWidthRatio(mFloatProps.get(INDEX_VisibleWidthRatio));
  }
  if ((mPrivateFlags & PFLAG_ALIGN_SELF_IS_SET) != 0L) {
    node.alignSelf((YogaAlign)mObjectProps.get(INDEX_AlignSelf));
  }
  if ((mPrivateFlags & PFLAG_POSITION_PERCENT_IS_SET) != 0L) {
    Edges mPositionPercents=(Edges)mObjectProps.get(INDEX_PositionPercents);
    ;
    for (int i=0, length=YogaEdge.values().length; i < length; i++) {
      final YogaEdge edge=YogaEdge.fromInt(i);
      final float value=mPositionPercents.getRaw(edge);
      if (!YogaConstants.isUndefined(value)) {
        node.positionPercent(edge,value);
      }
    }
  }
  if ((mPrivateFlags & PFLAG_FLEX_IS_SET) != 0L) {
    node.flex(mFloatProps.get(INDEX_Flex));
  }
  if ((mPrivateFlags & PFLAG_FLEX_GROW_IS_SET) != 0L) {
    node.flexGrow(mFloatProps.get(INDEX_FlexGrow));
  }
  if ((mPrivateFlags & PFLAG_FLEX_SHRINK_IS_SET) != 0L) {
    node.flexShrink(mFloatProps.get(INDEX_FlexShrink));
  }
  if ((mPrivateFlags & PFLAG_FLEX_BASIS_IS_SET) != 0L) {
    node.flexBasisPx(mIntProps.get(INDEX_FlexBasisPx));
  }
  if ((mPrivateFlags & PFLAG_FLEX_BASIS_PERCENT_IS_SET) != 0L) {
    node.flexBasisPercent(mFloatProps.get(INDEX_FlexBasisPercent));
  }
  if ((mPrivateFlags & PFLAG_WIDTH_PERCENT_IS_SET) != 0L) {
    node.widthPercent(mFloatProps.get(INDEX_WidthPercent));
  }
  if ((mPrivateFlags & PFLAG_MIN_WIDTH_IS_SET) != 0L) {
    node.minWidthPx(mIntProps.get(INDEX_MinWidthPx));
  }
  if ((mPrivateFlags & PFLAG_MIN_WIDTH_PERCENT_IS_SET) != 0L) {
    node.minWidthPercent(mFloatProps.get(INDEX_MinWidthPercent));
  }
  if ((mPrivateFlags & PFLAG_MAX_WIDTH_IS_SET) != 0L) {
    node.maxWidthPx(mIntProps.get(INDEX_MaxWidthPx));
  }
  if ((mPrivateFlags & PFLAG_MAX_WIDTH_PERCENT_IS_SET) != 0L) {
    node.maxWidthPercent(mFloatProps.get(INDEX_MaxWidthPercent));
  }
  if ((mPrivateFlags & PFLAG_HEIGHT_PERCENT_IS_SET) != 0L) {
    node.heightPercent(mFloatProps.get(INDEX_HeightPercentage));
  }
  if ((mPrivateFlags & PFLAG_MIN_HEIGHT_IS_SET) != 0L) {
    node.minHeightPx(mIntProps.get(INDEX_MinHeightPx));
  }
  if ((mPrivateFlags & PFLAG_MIN_HEIGHT_PERCENT_IS_SET) != 0L) {
    node.minHeightPercent(mFloatProps.get(INDEX_MinHeightPercent));
  }
  if ((mPrivateFlags & PFLAG_MAX_HEIGHT_IS_SET) != 0L) {
    node.maxHeightPx(mIntProps.get(INDEX_MaxHeightPx));
  }
  if ((mPrivateFlags & PFLAG_MAX_HEIGHT_PERCENT_IS_SET) != 0L) {
    node.maxHeightPercent(mFloatProps.get(INDEX_MaxHeightPercent));
  }
  if ((mPrivateFlags & PFLAG_ASPECT_RATIO_IS_SET) != 0L) {
    node.aspectRatio(mFloatProps.get(INDEX_AspectRatio));
  }
  if ((mPrivateFlags & PFLAG_IS_REFERENCE_BASELINE_IS_SET) != 0L) {
    node.isReferenceBaseline(mIsReferenceBaseline);
  }
  if ((mPrivateFlags & PFLAG_MARGIN_IS_SET) != 0L) {
    Edges mMargins=(Edges)mObjectProps.get(INDEX_Margins);
    for (int i=0, length=YogaEdge.values().length; i < length; i++) {
      final YogaEdge edge=YogaEdge.fromInt(i);
      final float value=mMargins.getRaw(edge);
      if (!YogaConstants.isUndefined(value)) {
        node.marginPx(edge,(int)value);
      }
    }
  }
  if ((mPrivateFlags & PFLAG_MARGIN_PERCENT_IS_SET) != 0L) {
    Edges mMarginPercents=(Edges)mObjectProps.get(INDEX_MarginPercents);
    for (int i=0, length=YogaEdge.values().length; i < length; i++) {
      final YogaEdge edge=YogaEdge.fromInt(i);
      final float value=mMarginPercents.getRaw(edge);
      if (!YogaConstants.isUndefined(value)) {
        node.marginPercent(edge,value);
      }
    }
  }
  if ((mPrivateFlags & PFLAG_MARGIN_AUTO_IS_SET) != 0L) {
    List<YogaEdge> mMarginAutos=(List<YogaEdge>)mObjectProps.get(INDEX_MarginAutos);
    for (    YogaEdge edge : mMarginAutos) {
      node.marginAuto(edge);
    }
  }
  if ((mPrivateFlags & PFLAG_PADDING_IS_SET) != 0L) {
    Edges mPaddings=(Edges)mObjectProps.get(INDEX_Paddings);
    for (int i=0, length=YogaEdge.values().length; i < length; i++) {
      final YogaEdge edge=YogaEdge.fromInt(i);
      final float value=mPaddings.getRaw(edge);
      if (!YogaConstants.isUndefined(value)) {
        node.paddingPx(edge,(int)value);
      }
    }
  }
  if ((mPrivateFlags & PFLAG_PADDING_PERCENT_IS_SET) != 0L) {
    Edges mPaddingPercents=(Edges)mObjectProps.get(INDEX_PaddingPercents);
    for (int i=0, length=YogaEdge.values().length; i < length; i++) {
      final YogaEdge edge=YogaEdge.fromInt(i);
      final float value=mPaddingPercents.getRaw(edge);
      if (!YogaConstants.isUndefined(value)) {
        node.paddingPercent(edge,value);
      }
    }
  }
  if ((mPrivateFlags & PFLAG_TOUCH_EXPANSION_IS_SET) != 0L) {
    Edges mTouchExpansions=(Edges)mObjectProps.get(INDEX_TouchExpansions);
    for (int i=0, length=YogaEdge.values().length; i < length; i++) {
      final YogaEdge edge=YogaEdge.fromInt(i);
      final float value=mTouchExpansions.getRaw(edge);
      if (!YogaConstants.isUndefined(value)) {
        node.touchExpansionPx(edge,(int)value);
      }
    }
  }
  if ((mPrivateFlags & PFLAG_BORDER_IS_SET) != 0L) {
    node.border((Border)mObjectProps.get(INDEX_Border));
  }
  if ((mPrivateFlags & PFLAG_STATE_LIST_ANIMATOR_IS_SET) != 0L) {
    node.stateListAnimator((StateListAnimator)mObjectProps.get(INDEX_StateListAnimator));
  }
  if ((mPrivateFlags & PFLAG_STATE_LIST_ANIMATOR_RES_IS_SET) != 0L) {
    node.stateListAnimatorRes(mIntProps.get(INDEX_StateListAnimatorRes));
  }
  if ((mPrivateFlags & PFLAG_USE_HEIGHT_AS_BASELINE_IS_SET) != 0L) {
    node.useHeightAsBaseline(mUseHeightAsBaseline);
  }
}
