/** 
 * Type cast to array E[]
 * @param object the input object to cast
 * @return casted array of type E[]
 */
@SuppressWarnings("unchecked") public static <E>E[] cast(Object[] object){
  return (E[])(object);
}
