/** 
 * @since 3.0.4
 */
public static <T>Update<T,UpdateParamEntity<T>> newUpdate(){
  return new Update<>(new UpdateParamEntity<>());
}
