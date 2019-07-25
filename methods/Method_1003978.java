@Override public <E extends Exception>void execute(String name,ExceptionalCommand<E> task,Class<E> exceptionClass,int numTries,Amount<Long,Time> retryDelay){
  blockingQueue.add(new RetryingRunnable<E>(name,task,exceptionClass,numTries,retryDelay,this));
}
