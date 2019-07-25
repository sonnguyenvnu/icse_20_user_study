/** 
 * Repeats this Solo indefinitely.
 * @return the new Flowable instance
 */
public final Flowable<T> repeat(){
  return Flowable.fromPublisher(this).repeat();
}
