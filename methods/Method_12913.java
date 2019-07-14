/** 
 * Case insensitive alternative to  {@link Enum#valueOf(Class,String)}.
 * @param < E > the concrete Enum type
 * @param enumValues the array of all Enum constants in question, usually per Enum.values()
 * @param constant the constant to get the enum value of
 * @throws IllegalArgumentException if the given constant is not found in the given arrayof enum values. Use  {@link #containsConstant(Enum[],String)} as a guard to avoid this exception.
 */
public static <E extends Enum<?>>E caseInsensitiveValueOf(E[] enumValues,String constant){
  for (  E candidate : enumValues) {
    if (candidate.toString().equalsIgnoreCase(constant)) {
      return candidate;
    }
  }
  throw new IllegalArgumentException(String.format("constant [%s] does not exist in enum type %s",constant,enumValues.getClass().getComponentType().getName()));
}
