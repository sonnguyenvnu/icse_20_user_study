protected <T>T requireStarted(final T object){
  if (object == null) {
    throw new JoyException("Component is not started yet and can not be used.");
  }
  return object;
}
