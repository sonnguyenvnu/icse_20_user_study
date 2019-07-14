/** 
 * A predicate that determines whether {@link #process(org.janusgraph.diskstorage.StaticBuffer,java.util.Map,ScanMetrics)}should be invoked for the given key.  If the predicate returns true, then users of this interface should invoke  {@code process} for the key andits associated entries.  If the predicate returns false, then users of this interface need not invoke  {@code process} for the key and its associated entries.<p> This is essentially an optimization that lets implementations of this interface signal to client code that a row can be safely skipped without affecting the execution of this  {@code ScanJob}. <p> The returned predicate may be called by concurrent threads in a single process.
 * @return a threadsafe predicate for edgestore keys
 */
default Predicate<StaticBuffer> getKeyFilter(){
  return b -> true;
}
