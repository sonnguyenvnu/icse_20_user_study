/** 
 * Creates a new JSON codec instance for objects of the specified class.
 * @param clazz the class of the objects the created codec is for.
 * @return a newly constructed JSON codec instance for objects of the requested class.
 */
public static <T>JsonCodec<T> create(Class<T> clazz){
  return new JsonCodec<T>(clazz,DefaultGsonHolder.instance);
}
