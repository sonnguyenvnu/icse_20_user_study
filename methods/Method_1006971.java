/** 
 * Clear the current context at the end of a batch - should only be used by {@link RepeatOperations} implementations.
 * @return the old value if there was one.
 */
public static RepeatContext clear(){
  RepeatContext context=getContext();
  RepeatSynchronizationManager.contextHolder.set(null);
  return context;
}
