/** 
 * Backing  {@link ChronicleMap#get},  {@link ChronicleMap#getUsing} and{@link ChronicleMap#getOrDefault} methods.
 * @implNote the default implementation is equivalent to <pre>{@code MapEntry<K, V> entry = q.entry(); if (entry != null) returnValue.returnValue(entry.value());}</pre>
 */
default void get(MapQueryContext<K,V,R> q,ReturnValue<V> returnValue){
  returnCurrentValueIfPresent(q,returnValue);
}
