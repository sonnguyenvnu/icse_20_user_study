public void run() throws Exception {
  if (!MemoryMeter.hasInstrumentation()) {
    out.println("WARNING: Java agent not installed - guessing instead");
  }
  out.println();
  unbounded();
  maximumSize();
  maximumSize_expireAfterAccess();
  maximumSize_expireAfterWrite();
  maximumSize_refreshAfterWrite();
  maximumWeight();
  expireAfterAccess();
  expireAfterWrite();
  expireAfterAccess_expireAfterWrite();
  weakKeys();
  weakValues();
  weakKeys_weakValues();
  weakKeys_softValues();
  softValues();
}
