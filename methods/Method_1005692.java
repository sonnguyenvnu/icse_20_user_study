/** 
 * @param name a descriptor like "Ljava/lang/Class;".
 */
public static <T>TypeId<T> get(String name){
  return new TypeId<>(name,external.com.android.dx.rop.type.Type.internReturnType(name));
}
