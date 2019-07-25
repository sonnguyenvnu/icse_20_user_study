/** 
 * Method for registering a context - should only be used by {@link RepeatOperations} implementations to ensure that{@link #getContext()} always returns the correct value.
 * @param context a new context at the start of a batch.
 * @return the old value if there was one.
 */
public static RepeatContext register(RepeatContext context){
  RepeatContext oldSession=getContext();
  RepeatSynchronizationManager.contextHolder.set(context);
  return oldSession;
}
