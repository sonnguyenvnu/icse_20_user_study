/** 
 * Monitor the CompoundServerSet. If any one of the monitor calls to the underlying serverSet raises a MonitorException, the exception is propagated. The call is successful only if all the monitor calls to the underlying serverSets are successful. NOTE: If an exception occurs during the monitor call, the serverSets in the composite will not be monitored.
 * @param monitor HostChangeMonitor instance used to monitor host changes.
 * @return A command that, when executed, will stop monitoring all underlying server sets.
 * @throws MonitorException If there was a problem monitoring any of the underlying server sets.
 */
@Override public synchronized Command watch(HostChangeMonitor<ServiceInstance> monitor) throws MonitorException {
  if (stopWatching == null) {
    monitors.add(monitor);
    ImmutableList.Builder<Command> commandsBuilder=ImmutableList.builder();
    for (    final ServerSet serverSet : serverSets) {
      commandsBuilder.add(serverSet.watch(new HostChangeMonitor<ServiceInstance>(){
        @Override public void onChange(        ImmutableSet<ServiceInstance> hostSet){
          handleChange(serverSet,hostSet);
        }
      }
));
    }
    stopWatching=Commands.compound(commandsBuilder.build());
  }
  return stopWatching;
}
