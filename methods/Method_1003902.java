/** 
 * Creates a new JSON codec instance for objects of the specified class and the specified Gson instance. You can use this method if you need to customize the behavior of the Gson serializer.
 * @param clazz the class of the objects the created codec is for.
 * @param gson the Gson instance to use for serialization/deserialization.
 * @return a newly constructed JSON codec instance for objects of the requested class.
 */
public static <T>JsonCodec<T> create(Class<T> clazz,Gson gson){
  return new JsonCodec<T>(clazz,gson);
}
