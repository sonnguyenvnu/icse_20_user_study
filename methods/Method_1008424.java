public static PrioritizedRunnable wrap(Runnable runnable,Priority priority){
  return new Wrapped(runnable,priority);
}
