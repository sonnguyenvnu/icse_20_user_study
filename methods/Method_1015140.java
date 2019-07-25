/** 
 * @param key   a primitive {@code long} key
 * @param value a value
 * @param merge a function which will be invoked if there is a pre-existing value under {@code key}, with the current value as the first argument and new value as the second, to determine the combined result
 * @return an updated map
 */
public FloatMap<V> put(double key,V value,BinaryOperator<V> merge){
  return put(key,value,merge,isLinear() ? map.editor : new Object());
}
