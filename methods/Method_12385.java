@Override public Flux<Instance> findByName(String name){
  return findAll().filter(a -> a.isRegistered() && name.equals(a.getRegistration().getName()));
}
