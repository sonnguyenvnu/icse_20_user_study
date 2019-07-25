/** 
 * Relays values until the other Publisher signals false and resumes if the other Publisher signals true again, like closing and opening a valve and not losing any items from the main source and starts with the specified valve state. <p>Properties: <ul> <li>If the other Publisher completes, the sequence terminates with an  {@code IllegalStateException}.</li> <li>The operator doesn't run on any particular  {@link io.reactivex.Scheduler Scheduler}.</li> <li>The operator is a pass-through for backpressure and uses an internal unbounded buffer of size  {@link Flowable#bufferSize()} to hold onto values if the valve is closed.</li></ul>
 * @param < T > the value type of the main source
 * @param other the other source
 * @param defaultOpen should the valve start as open?
 * @return the new FlowableTransformer instance
 * @throws NullPointerException if {@code other} is null
 * @since 0.7.2
 */
@SchedulerSupport(SchedulerSupport.NONE) @BackpressureSupport(BackpressureKind.PASS_THROUGH) public static <T>FlowableTransformer<T,T> valve(Publisher<Boolean> other,boolean defaultOpen){
  return valve(other,defaultOpen,Flowable.bufferSize());
}
