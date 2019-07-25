/** 
 * @deprecated a client should migrate to the {@link #search(Set,SearchScope,Consumer,ProgressMonitor)} instead
 */
@Deprecated @NotNull public Set<T> search(Set<R> elements,SearchScope scope,@NotNull ProgressMonitor monitor){
  CollectConsumer<T> consumer=new CollectConsumer<>();
  search(elements,scope,consumer,monitor);
  return new LinkedHashSet<>(consumer.getResult());
}
