/** 
 * Increments a counter and returns the next  {@link Resource} instance fromthe input, or  {@code null} if none remain.
 */
@Override @Nullable public synchronized Resource read() throws Exception {
  int index=counter.incrementAndGet() - 1;
  if (index >= resources.length) {
    return null;
  }
  return resources[index];
}
