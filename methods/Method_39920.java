/** 
 * Prepares step value. If step is 0, it will be set to +1 or -1, depending on start and end value. <p> If autoDirection flag is <code>true</code> then it is assumed that step is positive, and that direction (step sign) should be detected from start and end value. <p> If checkDirection flag is <code>true</code> than it checks loop direction (step sign) based on start and end value. Throws an exception if direction is invalid. If autoDirection is set, direction checking is skipped.
 */
protected void prepareStepDirection(final boolean autoDirection,final boolean checkDirection){
  if (step == 0) {
    step=(start <= end) ? 1 : -1;
    return;
  }
  if (autoDirection) {
    if (step < 0) {
      throw new IllegalArgumentException("Step value can't be negative: " + step);
    }
    if (start > end) {
      step=-step;
    }
    return;
  }
  if (checkDirection) {
    if (start < end) {
      if (step < 0) {
        throw new IllegalArgumentException("Negative step value for increasing loop");
      }
      return;
    }
    if (start > end) {
      if (step > 0) {
        throw new IllegalArgumentException("Positive step value for decreasing loop");
      }
    }
  }
}
