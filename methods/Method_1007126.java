/** 
 * Constructs an optional value that has no value.
 * @return An optional value that has no value.
 */
public static <T>Option<T> none(){
  return new None<>();
}
