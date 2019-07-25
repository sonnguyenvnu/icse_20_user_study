/** 
 * Relays values until the other Publisher signals false and resumes if the other Publisher signals true again, like closing and opening a valve and not losing any items from the main source and starts with the specified valve state and the specified buffer size hint. <p>Properties: <ul> <li>If the other Publisher completes, the sequence terminates with an  {@code IllegalStateException}.</li> <li>The operator doesn't run on any particular  {@link io.reactivex.Scheduler Scheduler}.</li> </ul>
 * @param < T > the value type of the main source
 * @param other the other source
 * @param defaultOpen should the valve start as open?
 * @param bufferSize the buffer size hint (the chunk size of the underlying unbounded buffer)
 * @return the new FlowableTransformer instance
 * @throws IllegalArgumentException if bufferSize &lt;= 0
 * @throws NullPointerException if {@code other} is null
 * @since 0.7.2
 */
@SchedulerSupport(SchedulerSupport.NONE) @BackpressureSupport(BackpressureKind.PASS_THROUGH) public static <T>FlowableTransformer<T,T> valve(Publisher<Boolean> other,boolean defaultOpen,int bufferSize){
  ObjectHelper.requireNonNull(other,"other is null");
  ObjectHelper.verifyPositive(bufferSize,"bufferSize");
  return new FlowableValve<T>(null,other,defaultOpen,bufferSize);
}
