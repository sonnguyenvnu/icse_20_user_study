/** 
 * Backing  {@link ChronicleMap#merge(Object,Object,BiFunction)} method.
 * @implNote the default implementation is equivalent to <pre>{@code q.updateLock().lock(); Data<V, ?> newValueData; MapEntry<K, V> entry = q.entry();}if (entry != null)  V oldValue = entry.value().get(); V newValue = remappingFunction.apply(oldValue, value.get()); if (newValue == null) { q.remove(entry); return; } newValueData = q.wrapValueAsData(newValue); q.replaceValue(entry, newValueData); } else { newValueData = value; q.insert(q.absentEntry(), newValueData); entry = q.entry(); } returnValue.returnValue(entry.value()); }</pre>
 */
default void merge(MapQueryContext<K,V,R> q,Data<V> value,BiFunction<? super V,? super V,? extends V> remappingFunction,ReturnValue<V> returnValue){
  q.updateLock().lock();
  Data<V> newValueData;
  MapEntry<K,V> entry=q.entry();
  if (entry != null) {
    V oldValue=entry.value().get();
    V newValue=remappingFunction.apply(oldValue,value.get());
    if (newValue == null) {
      q.remove(entry);
      return;
    }
    newValueData=q.wrapValueAsData(newValue);
    q.replaceValue(entry,newValueData);
  }
 else {
    newValueData=value;
    q.insert(q.absentEntry(),newValueData);
    entry=q.entry();
    assert entry != null;
  }
  returnValue.returnValue(entry.value());
}
