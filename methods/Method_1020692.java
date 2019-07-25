/** 
 * Create a TestSubscriber, optionally cancel it, subscribe it to this Nono and return the TestSubscriber itself.
 * @param cancelled shoud the TestSubscriber be cancelled before the subscription
 * @return the TestSubscriber created
 */
@SchedulerSupport(SchedulerSupport.NONE) public final TestSubscriber<Void> test(boolean cancelled){
  TestSubscriber<Void> ts=new TestSubscriber<Void>();
  if (cancelled) {
    ts.cancel();
  }
  subscribe(ts);
  return ts;
}
