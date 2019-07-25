/** 
 * Backing  {@link ChronicleMap#compute(Object,BiFunction)} method.
 * @implNote the default implementation is equivalent to <pre>{@code q.updateLock().lock(); MapEntry<K, V> entry = q.entry(); V oldValue = entry != null ? entry.value().get() : null; V newValue = remappingFunction.apply(q.queriedKey().get(), oldValue);}if (newValue != null)  Data<V, ?> newValueData = q.wrapValueAsData(newValue); if (entry != null) { q.replaceValue(entry, newValueData); } else { q.insert(q.absentEntry(), newValueData); entry = q.entry(); } returnValue.returnValue(entry.value()); } else if (entry != null) { q.remove(entry); }}</pre>
 */
default void compute(MapQueryContext<K,V,R> q,BiFunction<? super K,? super V,? extends V> remappingFunction,ReturnValue<V> returnValue){
  q.updateLock().lock();
  MapEntry<K,V> entry=q.entry();
  V oldValue=entry != null ? entry.value().get() : null;
  V newValue=remappingFunction.apply(q.queriedKey().get(),oldValue);
  if (newValue != null) {
    Data<V> newValueData=q.wrapValueAsData(newValue);
    if (entry != null) {
      q.replaceValue(entry,newValueData);
    }
 else {
      q.insert(q.absentEntry(),newValueData);
      entry=q.entry();
      assert entry != null;
    }
    returnValue.returnValue(entry.value());
  }
 else   if (entry != null) {
    q.remove(entry);
  }
}
