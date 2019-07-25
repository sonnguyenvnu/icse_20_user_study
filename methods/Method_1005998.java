/** 
 * Create a new  {@code WebSocket} instance that has the same settingsas this instance. Note that, however, settings you made on the raw socket are not copied. <p> The  {@link WebSocketFactory} instance that you used to create this{@code WebSocket} instance is used again.</p>
 * @return A new  {@code WebSocket} instance.
 * @param timeout The timeout value in milliseconds for socket timeout. A timeout of zero is interpreted as an infinite timeout.
 * @throws IllegalArgumentException The given timeout value is negative.
 * @throws IOException {@link WebSocketFactory#createSocket(URI)} threw an exception.
 * @since 1.10
 */
public WebSocket recreate(int timeout) throws IOException {
  if (timeout < 0) {
    throw new IllegalArgumentException("The given timeout value is negative.");
  }
  WebSocket instance=mWebSocketFactory.createSocket(getURI(),timeout);
  instance.mHandshakeBuilder=new HandshakeBuilder(mHandshakeBuilder);
  instance.setPingInterval(getPingInterval());
  instance.setPongInterval(getPongInterval());
  instance.setPingPayloadGenerator(getPingPayloadGenerator());
  instance.setPongPayloadGenerator(getPongPayloadGenerator());
  instance.mExtended=mExtended;
  instance.mAutoFlush=mAutoFlush;
  instance.mMissingCloseFrameAllowed=mMissingCloseFrameAllowed;
  instance.mDirectTextMessage=mDirectTextMessage;
  instance.mFrameQueueSize=mFrameQueueSize;
  List<WebSocketListener> listeners=mListenerManager.getListeners();
synchronized (listeners) {
    instance.addListeners(listeners);
  }
  return instance;
}
