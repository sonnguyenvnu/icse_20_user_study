/** 
 * Returns the execution controller bound to the current thread, or throws an exception if called on a non Ratpack managed compute thread. <p> If called on a non Ratpack compute thread, the returned optional will be empty.
 * @return the execution controller for the current thread
 * @throws UnmanagedThreadException when called from a non Ratpack managed thread
 */
static ExecController require() throws UnmanagedThreadException {
  return current().orElseThrow(UnmanagedThreadException::new);
}
