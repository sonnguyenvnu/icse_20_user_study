/** 
 * @return whether the associated Job class carries the {@link DisallowConcurrentExecution} annotation.
 */
public boolean isConcurrentExectionDisallowed(){
  return ClassUtils.isAnnotationPresent(jobClass,DisallowConcurrentExecution.class);
}
