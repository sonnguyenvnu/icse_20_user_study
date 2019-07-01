/** 
 * Whether to preserve MDC for tasks in Executor.
 * @param enabled flag to enable/disable MDC preservation in Executor.
 * @return configuration.
 */
public T _XXXXX_(boolean enabled){
  setProperty(PRESERVE_MDC_FOR_TASK_EXECUTION,enabled);
  return getThis();
}