@Override public void doTag() throws JspException {
  if (!isEndSpecified) {
    throw new IllegalArgumentException("End boundary of the loop is not specified");
  }
  prepareStepDirection();
  if (isCount) {
    if (end < 0) {
      throw new IllegalArgumentException("Negative count value");
    }
    end=start + step * (end - 1);
  }
  prepareStepDirection(autoDirection,false);
  if (isExclusive) {
    if (step > 0) {
      this.end--;
    }
 else     if (step < 0) {
      this.end++;
    }
  }
  loopBody();
}
