@ManagedOperation(description="Dumps unicast and multicast tables") public String dump(){
  return String.format("\nmcasts:\n%s\nucasts:\n%s",mcasts,ucasts);
}
