protected void loop() throws Exception {
  List<String> lock_names;
  while (ch.isConnected()) {
    String line=Util.readStringFromStdin(": ");
    if (line == null || line.startsWith("quit") || line.startsWith("exit"))     break;
    if (line.startsWith("lock")) {
      lock_names=parseLockNames(line.substring("lock".length()).trim());
      for (      String lock_name : lock_names) {
        Lock lock=lock_service.getLock(lock_name);
        lock.lock();
      }
    }
 else     if (line.startsWith("trylock")) {
      lock_names=parseLockNames(line.substring("trylock".length()).trim());
      String tmp=lock_names.get(lock_names.size() - 1);
      Long timeout=(long)-1;
      try {
        timeout=Long.parseLong(tmp);
        lock_names.remove(lock_names.size() - 1);
      }
 catch (      NumberFormatException e) {
      }
      for (      String lock_name : lock_names) {
        Lock lock=lock_service.getLock(lock_name);
        boolean rc;
        if (timeout < 0)         rc=lock.tryLock();
 else         rc=lock.tryLock(timeout,TimeUnit.MILLISECONDS);
        if (!rc)         System.err.println("Failed locking \"" + lock_name + "\"");
      }
    }
 else     if (line.startsWith("unlock")) {
      lock_names=parseLockNames(line.substring("unlock".length()).trim());
      for (      String lock_name : lock_names) {
        if (lock_name.equalsIgnoreCase("all")) {
          lock_service.unlockAll();
          break;
        }
 else {
          Lock lock=lock_service.getLock(lock_name);
          if (lock != null)           lock.unlock();
        }
      }
    }
 else     if (line.startsWith("view"))     System.out.println("View: " + ch.getView());
 else     if (line.startsWith("help"))     help();
    printLocks();
  }
}
