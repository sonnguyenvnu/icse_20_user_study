/** 
 * Casts a nullable type array to a non-null type array without runtime null check. 
 */
@SuppressWarnings({"contracts.postcondition.not.satisfied","return.type.incompatible"}) @EnsuresNonNull("#1") public static <T>T[] castNonNullTypeArray(@NullableType T[] value){
  return value;
}
