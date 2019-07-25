/** 
 * Adds callback function
 * @param callback the callback function: either exception/1 or message/1.
 */
@Contract(pure=false) public void callback(@NotNull final CallDefinition callback){
  assert callback.getArity() == 1;
  assert callback.time() == Timed.Time.RUN;
  String callbackName=callback.name();
  assert callbackName.equals("exception") || callbackName.equals("message");
  if (callbacks == null) {
    callbacks=new ArrayList<CallDefinition>();
  }
  callbacks.add(callback);
}
