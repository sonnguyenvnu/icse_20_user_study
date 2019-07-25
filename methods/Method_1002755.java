/** 
 * Closes the deferred stream without setting a delegate.
 * @throws IllegalStateException if the delegate has been set already orif  {@link #close()} or {@link #close(Throwable)} was called already.
 */
public void close(Throwable cause){
  requireNonNull(cause,"cause");
  final DefaultStreamMessage<T> m=new DefaultStreamMessage<>();
  m.close(cause);
  delegate(m);
}
