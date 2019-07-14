private OptimisticLockingException createOptimisticLockException(InstanceEvent event,long lastVersion){
  return new OptimisticLockingException("Verison " + event.getVersion() + " was overtaken by " + lastVersion + " for " + event.getInstance());
}
