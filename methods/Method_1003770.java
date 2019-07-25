/** 
 * Returns a publisher that emits only the first  {@code n} elements from the given publisher, where {@code n} is thegiven count. <p> On emitting the required number of elements, the upstream subscription will be cancelled and the stream completed. <p> The given required number of elements is a maximum. If the upstream publisher completes before the required number of elements is reached, then the stream completes as normal.
 * @param count the max number of items to emit before completing
 * @param upstreamPublisher the publisher to take from
 * @param < T > the type of emitted item
 * @return a publisher that will emit a max of {@code n} elements
 * @since 1.5
 */
public static <T>TransformablePublisher<T> take(long count,Publisher<T> upstreamPublisher){
  return new TakePublisher<>(count,upstreamPublisher);
}
