private void checkClassName(String className){
  if (className.indexOf('/') >= 0 || className.indexOf('\\') >= 0) {
    throw new IllegalArgumentException("Invalid class name: " + className);
  }
}
