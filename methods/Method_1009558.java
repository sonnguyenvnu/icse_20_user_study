protected Collection<D> find(ServiceType serviceType,D current){
  Collection<S> services=findServices(serviceType,null,current);
  Collection<D> devices=new HashSet<>();
  for (  Service service : services) {
    devices.add((D)service.getDevice());
  }
  return devices;
}
