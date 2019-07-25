/** 
 * Compares two given objects by using  {@link Object#equals(Object)} and accepting nullreferences.
 */
@Contract("null, null -> true; null, _ -> false; _, null -> false") public static <T>boolean equals(@Nullable T o1,@Nullable T o2){
  return o1 == null && o2 == null || o1 != null && o1.equals(o2);
}
