/** 
 * @return whether the associated Job class carries the {@link PersistJobDataAfterExecution} annotation.
 */
public boolean isPersistJobDataAfterExecution(){
  return ClassUtils.isAnnotationPresent(jobClass,PersistJobDataAfterExecution.class);
}
