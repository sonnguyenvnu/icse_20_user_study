@Override public void visit(VmOptionLogMessage logMessage){
  vmOptionsBuilder.put(logMessage.name(),logMessage.value());
}
