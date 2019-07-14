@Override public StackTraceElement[] getStackTrace(){
  if (null != errors) {
    List<StackTraceElement> stackTraceElements=errors.stream().map(Exception::getStackTrace).flatMap(Stream::of).collect(Collectors.toList());
    stackTraceElements.addAll(Arrays.asList(super.getStackTrace()));
    return stackTraceElements.toArray(new StackTraceElement[stackTraceElements.size()]);
  }
  return super.getStackTrace();
}
