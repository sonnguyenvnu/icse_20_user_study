public void setAsText(String text) throws IllegalArgumentException {
  super.setValue(new SharedConsumerSecretImpl(text));
}
