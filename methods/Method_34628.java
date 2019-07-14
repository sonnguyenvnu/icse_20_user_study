private boolean isTerminated(){
  return (subject.hasCompleted() || subject.hasThrowable());
}
