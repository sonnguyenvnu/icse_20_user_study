@Override void validate() throws WebSocketException {
  for (  Map.Entry<String,String> entry : getParameters().entrySet()) {
    validateParameter(entry.getKey(),entry.getValue());
  }
  mIncomingSlidingWindowBufferSize=mServerWindowSize + INCOMING_SLIDING_WINDOW_MARGIN;
}
