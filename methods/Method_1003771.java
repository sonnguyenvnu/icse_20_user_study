/** 
 * See  {@link Streams#wiretap(Publisher,Action)}.
 * @param listener the listener for emitted items
 * @return a publisher that is logically equivalent to the given publisher as far as subscribers are concerned
 */
default TransformablePublisher<T> wiretap(Action<? super StreamEvent<T>> listener){
  return Streams.wiretap(this,listener);
}
