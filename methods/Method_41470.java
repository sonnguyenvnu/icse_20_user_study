public synchronized Collection<Scheduler> lookupAll(){
  return java.util.Collections.unmodifiableCollection(schedulers.values());
}
