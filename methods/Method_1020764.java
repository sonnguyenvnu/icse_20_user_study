/** 
 * Relays values until the other ObservableSource signals false and resumes if the other ObservableSource signals true again, like closing and opening a valve and not losing any items from the main source and starts with the specified valve state. <p>Properties: <ul> <li>If the other ObservableSource completes, the sequence terminates with an  {@code IllegalStateException}.</li> <li>The operator doesn't run on any particular  {@link io.reactivex.Scheduler Scheduler}.</li> <li>The operator uses an internal unbounded buffer of size  {@link Flowable#bufferSize()} to hold onto values if the valve is closed.</li></ul>
 * @param < T > the value type of the main source
 * @param other the other source
 * @param defaultOpen should the valve start as open?
 * @return the new ObservableTransformer instance
 * @throws NullPointerException if {@code other} is null
 * @since 0.20.2
 */
@SchedulerSupport(SchedulerSupport.NONE) public static <T>ObservableTransformer<T,T> valve(ObservableSource<Boolean> other,boolean defaultOpen){
  return valve(other,defaultOpen,Flowable.bufferSize());
}
