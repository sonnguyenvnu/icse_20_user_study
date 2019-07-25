@Override public Command watch(HostChangeMonitor<ServiceInstance> monitor){
  monitor.onChange(hosts);
  return Commands.NOOP;
}
