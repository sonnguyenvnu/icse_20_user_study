private void checkLayoutParams(LayoutParams lp,boolean horizontal){
  String groupName=horizontal ? "column" : "row";
  Spec spec=horizontal ? lp.columnSpec : lp.rowSpec;
  Interval span=spec.span;
  if (span.min != UNDEFINED && span.min < 0) {
    handleInvalidParams(groupName + " indices must be positive");
  }
  Axis axis=horizontal ? mHorizontalAxis : mVerticalAxis;
  int count=axis.definedCount;
  if (count != UNDEFINED) {
    if (span.max > count) {
      handleInvalidParams(groupName + " indices (start + span) mustn't exceed the " + groupName + " count");
    }
    if (span.size() > count) {
      handleInvalidParams(groupName + " span mustn't exceed the " + groupName + " count");
    }
  }
}
