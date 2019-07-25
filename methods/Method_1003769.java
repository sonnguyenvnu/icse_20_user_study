/** 
 * Returns a publisher that merges the given input publishers into a single stream of elements. <p> The returned publisher obeys the following rules: <ul> <li> Only when all given input publishers have signalled completion will the downstream subscriber be completed. </li> <li> If one of the given input publishers errors then all other publisher subscriptions are cancelled and the error is propagated to the subscriber. </li> <li> If the subscription of the returned publisher is cancelled by the subscriber then all given input publisher subscriptions are cancelled. </li> </ul> <p> The returned publisher is implicitly buffered to respect back pressure via  {@link #buffer(org.reactivestreams.Publisher)}.
 * @param publishers the data sources to merge
 * @param < T > the type of item emitted
 * @return a publisher that emits a single stream of elements from multiple publishers
 */
@SuppressWarnings({"unchecked","varargs"}) @SafeVarargs public static <T>TransformablePublisher<T> merge(Publisher<? extends T>... publishers){
  return new MergingPublisher<>(publishers).buffer();
}
