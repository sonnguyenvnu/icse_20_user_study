/** 
 * Get the generic  {@link ExecutorService}. This executor service {@link Executor#execute(Runnable)} method will run the {@link Runnable} it is given in the{@link ThreadContext} of the thread that queues it.<p> Warning: this  {@linkplain ExecutorService} will not throw {@link RejectedExecutionException}if you submit a task while it shutdown. It will instead silently queue it and not run it.
 */
public ExecutorService generic(){
  return executor(Names.GENERIC);
}
