public void update(ExecutionContext executionContext) throws ItemStreamException {
  executionContext.putInt(EXPECTED,localState.expected.intValue());
  executionContext.putInt(ACTUAL,localState.actual.intValue());
}
