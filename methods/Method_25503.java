/** 
 * Given the class representation of a type, supplies the corresponding type. 
 */
public static Supplier<Type> typeFromClass(Class<?> inputClass){
  return typeFromString(inputClass.getName());
}
