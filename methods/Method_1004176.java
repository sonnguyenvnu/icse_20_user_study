static synchronized void add(VanillaChronicleHash hash){
  if (maps == null)   throw new IllegalStateException("Shutdown in progress");
  maps.put(hash.identity,order++);
}
