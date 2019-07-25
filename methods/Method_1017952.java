public void retry(String processorId){
  Processor processor=processors.get(processorId);
  if (processor == null) {
    throw new RuntimeException("Unable to retry savepoint for processor [" + processorId + "]. No flowfiles registered.");
  }
  if (processor.state.isRelease()) {
    throw new RuntimeException("Unable to retry savepoint for processor [" + processorId + "]. Already released.");
  }
  processor.setState(SavePointState.RETRY);
}
