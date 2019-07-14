protected void requireNotStarted(final Object object){
  if (object != null) {
    throw new JoyException("Configuration is modified after component is started.");
  }
}
