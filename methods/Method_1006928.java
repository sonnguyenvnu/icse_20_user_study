@Override @SuppressWarnings("unchecked") public void open(ExecutionContext executionContext){
  if (isSaveState()) {
    startAfterValues=(Map<String,Object>)executionContext.get(getExecutionContextKey(START_AFTER_VALUE));
    if (startAfterValues == null) {
      startAfterValues=new LinkedHashMap<>();
    }
  }
  super.open(executionContext);
}
