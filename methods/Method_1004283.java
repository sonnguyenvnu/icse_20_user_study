/** 
 * Creates a new  {@link Input} instance that is always undefined.
 * @param value to be wrapped
 * @return a new {@link Input} instance
 */
public static <V>Input<V> absent(){
  return new Input<>(null,false);
}
