public <T>Provider<T> scope(final Key<T> key,final Provider<T> creator){
  return new Provider<T>(){
    @SuppressWarnings("DoubleCheckedLocking") public T get(){
      if (instance == null) {
        try {
          final Object lock=getLock(key);
synchronized (lock) {
            if (instance == null) {
              T provided=creator.get();
              if (provided instanceof CircularDependencyProxy) {
                return provided;
              }
              Object providedOrSentinel=(provided == null) ? NULL : provided;
              if ((instance != null) && (instance != providedOrSentinel)) {
                throw new ProvisionException("Provider was reentrant while creating a singleton");
              }
              instance=providedOrSentinel;
            }
          }
        }
  finally {
          releaseLock(key);
        }
      }
      Object localInstance=instance;
      @SuppressWarnings({"unchecked","UnnecessaryLocalVariable"}) T returnedInstance=(localInstance != NULL) ? (T)localInstance : null;
      return returnedInstance;
    }
    public String toString(){
      return String.format("%s[%s]",creator,instance);
    }
  }
;
}
