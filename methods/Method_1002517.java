/** 
 * The standard  {@link ChannelPoolManagerFactoryImpl} is stateless, and doesn't need to do any operation at shutdown
 */
@Override public void shutdown(Callback<None> callback){
  callback.onSuccess(None.none());
}
