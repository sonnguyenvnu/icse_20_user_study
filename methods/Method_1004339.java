/** 
 * Set the  {@link RealSubscriptionManager} to a connectible state. It is safe to call this methodat any time.  Does nothing unless we are in the stopped state.
 */
@Override public void start(){
synchronized (this) {
    if (state == State.STOPPED) {
      setStateAndNotify(State.DISCONNECTED);
    }
  }
}
