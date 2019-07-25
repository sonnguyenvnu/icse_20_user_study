/** 
 * Attempts to lead the singleton service.
 * @param endpoint The primary endpoint to register as a leader candidate in the service.
 * @param additionalEndpoints Additional endpoints that are available on the host.
 * @param listener Handler to call when the candidate is elected or defeated.
 * @throws Group.WatchException If there was a problem watching the ZooKeeper group.
 * @throws Group.JoinException If there was a problem joining the ZooKeeper group.
 * @throws InterruptedException If the thread watching/joining the group was interrupted.
 */
public void lead(final InetSocketAddress endpoint,final Map<String,InetSocketAddress> additionalEndpoints,final LeadershipListener listener) throws Group.WatchException, Group.JoinException, InterruptedException {
  Preconditions.checkNotNull(listener);
  candidate.offerLeadership(new Leader(){
    @Override public void onElected(    final ExceptionalCommand<JoinException> abdicate){
      listener.onLeading(new LeaderControl(){
        @Override public synchronized void advertise() throws JoinException, InterruptedException {
          Preconditions.checkState(!left.get(),"Cannot advertise after leaving.");
          Preconditions.checkState(endpointStatus == null,"Cannot advertise more than once.");
          endpointStatus=serverSet.join(endpoint,additionalEndpoints);
        }
        @Override public synchronized void leave() throws UpdateException, JoinException {
          Preconditions.checkState(left.compareAndSet(false,true),"Cannot leave more than once.");
          if (endpointStatus != null) {
            endpointStatus.leave();
          }
          abdicate.execute();
        }
      }
);
    }
    @Override public void onDefeated(){
      listener.onDefeated(endpointStatus);
    }
  }
);
}
