/** 
 * Converts a focusDirection to orientation.
 * @param focusDirection One of {@link View#FOCUS_UP},  {@link View#FOCUS_DOWN}, {@link View#FOCUS_LEFT},  {@link View#FOCUS_RIGHT}, {@link View#FOCUS_BACKWARD},  {@link View#FOCUS_FORWARD}or 0 for not applicable
 * @return {@link LayoutState#LAYOUT_START} or {@link LayoutState#LAYOUT_END} if focus directionis applicable to current state,  {@link LayoutState#INVALID_LAYOUT} otherwise.
 */
private int convertFocusDirectionToLayoutDirection(int focusDirection){
switch (focusDirection) {
case View.FOCUS_BACKWARD:
    if (mOrientation == VERTICAL) {
      return LayoutState.LAYOUT_START;
    }
 else     if (isLayoutRTL()) {
      return LayoutState.LAYOUT_END;
    }
 else {
      return LayoutState.LAYOUT_START;
    }
case View.FOCUS_FORWARD:
  if (mOrientation == VERTICAL) {
    return LayoutState.LAYOUT_END;
  }
 else   if (isLayoutRTL()) {
    return LayoutState.LAYOUT_START;
  }
 else {
    return LayoutState.LAYOUT_END;
  }
case View.FOCUS_UP:
return mOrientation == VERTICAL ? LayoutState.LAYOUT_START : LayoutState.INVALID_LAYOUT;
case View.FOCUS_DOWN:
return mOrientation == VERTICAL ? LayoutState.LAYOUT_END : LayoutState.INVALID_LAYOUT;
case View.FOCUS_LEFT:
return mOrientation == HORIZONTAL ? LayoutState.LAYOUT_START : LayoutState.INVALID_LAYOUT;
case View.FOCUS_RIGHT:
return mOrientation == HORIZONTAL ? LayoutState.LAYOUT_END : LayoutState.INVALID_LAYOUT;
default :
if (DEBUG) {
Log.d(TAG,"Unknown focus request:" + focusDirection);
}
return LayoutState.INVALID_LAYOUT;
}
}
