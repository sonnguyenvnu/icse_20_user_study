/** 
 * Optional destination of all messages reported by generator, if none specified (or <code>null</code>), messages get discarded.
 * @param messages destination of generator messages, or <code>null</code>
 * @return <code>this</code> for convenience
 */
public GenerationFacade messages(@Nullable IMessageHandler messages){
  myMessageHandler=messages == null ? IMessageHandler.NULL_HANDLER : messages;
  return this;
}
