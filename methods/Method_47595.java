/** 
 * For the currently showing view (either hours or minutes), re-calculate the position for the selector, and redraw it at that position. The input degrees will be snapped to a selectable value.
 * @param degrees Degrees which should be selected.
 * @param isInnerCircle Whether the selection should be in the inner circle; will be ignoredif there is no inner circle.
 * @param forceToVisibleValue Even if the currently-showing circle allows for fine-grainedselection (i.e. minutes), force the selection to one of the visibly-showing values.
 * @param forceDrawDot The dot in the circle will generally only be shown when the selectionis on non-visible values, but use this to force the dot to be shown.
 * @return The value that was selected, i.e. 0-23 for hours, 0-59 for minutes.
 */
private int reselectSelector(int degrees,boolean isInnerCircle,boolean forceToVisibleValue,boolean forceDrawDot){
  if (degrees == -1) {
    return -1;
  }
  int currentShowing=getCurrentItemShowing();
  int stepSize;
  boolean allowFineGrained=!forceToVisibleValue && (currentShowing == MINUTE_INDEX);
  if (allowFineGrained) {
    degrees=snapPrefer30s(degrees);
  }
 else {
    degrees=snapOnly30s(degrees,0);
  }
  RadialSelectorView radialSelectorView;
  if (currentShowing == HOUR_INDEX) {
    radialSelectorView=mHourRadialSelectorView;
    stepSize=HOUR_VALUE_TO_DEGREES_STEP_SIZE;
  }
 else {
    radialSelectorView=mMinuteRadialSelectorView;
    stepSize=MINUTE_VALUE_TO_DEGREES_STEP_SIZE;
  }
  radialSelectorView.setSelection(degrees,isInnerCircle,forceDrawDot);
  radialSelectorView.invalidate();
  if (currentShowing == HOUR_INDEX) {
    if (mIs24HourMode) {
      if (degrees == 0 && isInnerCircle) {
        degrees=360;
      }
 else       if (degrees == 360 && !isInnerCircle) {
        degrees=0;
      }
    }
 else     if (degrees == 0) {
      degrees=360;
    }
  }
 else   if (degrees == 360 && currentShowing == MINUTE_INDEX) {
    degrees=0;
  }
  int value=degrees / stepSize;
  if (currentShowing == HOUR_INDEX && mIs24HourMode && !isInnerCircle && degrees != 0) {
    value+=12;
  }
  return value;
}
