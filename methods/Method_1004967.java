protected void validate(final SampleElementsForSplitPoints operation,final S store) throws OperationException {
  if (null == operation.getInput()) {
    throw new OperationException("Operation input is required.");
  }
}
